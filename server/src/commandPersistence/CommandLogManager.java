package commandPersistence;

import serverNode.ServerInfo;
import utilities.LoggerHelper;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Improvements:
 * File writing can be done in separate threads with each thread locking on write. Be sure to write in correct order.
 * could also use a queue and the file handler retrieves from queu since at a time only one thread reads or writes
 * so there is no point in having multiple threads waiting
 * <p>
 * Reading is fine with concurrent reads.
 * <p>
 * During existence check:
 * - override the Object.equals method so that the check can be done easily, perhaps can even use LinkedList.contains()
 * - read from the bottom of list (latest first)
 * - read only until currentIndex < searchIndex (as there is no point in going back)
 * <p>
 * Can you make all methods static? How to give a specific file name and make it static
 * <p>
 * Use try-with-resources for file operations
 * <p>
 * File knowing the EOF, ues a special marker or a separate file with the count of commands in the commandLog. When that count is read, stop. When persisting, update the count file as well.
 * <p>
 * Use AppendableObjectOutputStream to append into file rather than writing the whole list all the time
 * http://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream/1195078#1195078
 */

/*
todo only need one map per jvm so no need to create object. populate the list only once per jmv. move all methods to static. initialize hanlders only once. load only once. open and close write resources everytime.
 */
public class CommandLogManager {

    private static Logger log = LoggerHelper.getLogger(CommandLogManager.class.getName());

    LinkedList<CommandLogEntry> commandLogList;
    ServerInfo selfServerInfo;
    static File commandFile;
    static FileOutputStream fileOutputStream = null;
    static ObjectOutputStream objectOutputStream = null;
    static FileInputStream fileInputStream = null;
    static ObjectInputStream objectInputStream = null;

    public CommandLogManager(ServerInfo si) {
        selfServerInfo = si;
        commandLogList = new LinkedList<>();

        try {
            initializeFileHandlers(si);
            loadCommandLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Should only be called ONCE, during class static intialization
     * @param info
     * @throws Exception
     */
    private static void initializeFileHandlers(ServerInfo info) throws Exception {
        commandFile = new File("command_log_" + info.getServerName() + ".ser");
        // create file if it doesn't exist
        commandFile.createNewFile();

        // output hanlders
        fileOutputStream = new FileOutputStream(commandFile, true);
        objectOutputStream = new AppendableObjectOutputStream(fileOutputStream);

        // input hanlders
        fileInputStream = new FileInputStream(commandFile);
        objectInputStream = new ObjectInputStream(fileInputStream);
    }

    private void persistCommandLog() {
        try {
            for (CommandLogEntry entry : commandLogList) {
                objectOutputStream.writeObject(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCommandLog() {
        log.info("loadCommandLog() - Loading command log into memory.");
        try {
            while (true) {
                CommandLogEntry entry = (CommandLogEntry) objectInputStream.readObject();
                commandLogList.add(entry);
            }
        } catch (EOFException | NullPointerException e) {
            // do nothing since no commands have been persisted in this log or EOF has reached
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("loadCommandLog() - Loaded command log into memory. Command list size: " + commandLogList.size());
    }

    public boolean doesCommandExist(int index, int term) {
        for (CommandLogEntry entry : commandLogList) {
            if (entry.getTerm() == term && entry.getIndex() == index) {
                return true;
            }
        }
        return false;
    }

    public void insertAndTruncate(CommandLogEntry cle) {

        if (doesCommandExist(cle.getIndex(), cle.getTerm())) {
            // delete list and persist into file
            Iterator<CommandLogEntry> itr = commandLogList.iterator();
            boolean truncateFlag = false;
            while (itr.hasNext()) {
                CommandLogEntry entry = itr.next();
                if (entry.getTerm() == cle.getTerm() && entry.getIndex() == cle.getIndex()) {
                    truncateFlag = true;
                }

                if (truncateFlag) {
//                    itr.remove();
                    itr.forEachRemaining(new Consumer<CommandLogEntry>() {
                        @Override
                        public void accept(CommandLogEntry commandLogEntry) {
                            commandLogEntry.forEachTest = "foreach";
                        }
                    });
                }
            }
            System.out.println(commandLogList);
        }

        commandLogList.addLast(cle);
        persistCommandLog();
    }

    public String getHistory() {
        return commandLogList.toString();
    }

    public int getSize() {
        return commandLogList.size();
    }
}


/*
write-
get write lock
update file
release write lock

read-
acquire N read locks (for N readers)
read
release locks

// chance of writing starvation
 */