package commandPersistence;

import serverNode.ServerInfo;
import utilities.Constants;
import utilities.LoggerHelper;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;

public class CommandLogManager {

    private static Logger log = LoggerHelper.getLogger(CommandLogManager.class.getName());

    private static LinkedList<CommandLogEntry> commandLogList;
    private static ServerInfo selfServerInfo;
    private static File commandFile;

    static {
        initialize();
        loadCommandLog();
    }

    private static void initialize() {
        selfServerInfo = new ServerInfo(Constants.selfServerName);
        commandLogList = new LinkedList<>();

        // create/initialize the log file
        commandFile = new File("command_log_" + selfServerInfo.getServerName() + ".ser");
        try {
            // create file if it doesn't exist
            if (commandFile.createNewFile()) {
                log.info("Creating new file.");
            } else {
                log.info("Existing file initialized.");
            }
        } catch (IOException e) {
            log.severe(e.toString());
        }
    }

    private static void persistCommandLog() {
        // output hanlders

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(commandFile);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            for (CommandLogEntry entry : commandLogList) {
                objectOutputStream.writeObject(entry);
            }
            log.info("Command logs persisted. Total commands written: " + commandLogList.size() + ". Last command: " + commandLogList.getLast());
        } catch (IOException e) {
            log.severe(e.toString());
        }
    }

    private static void loadCommandLog() {
        // input hanlders

        try (
                FileInputStream fileInputStream = new FileInputStream(commandFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {

            CommandLogEntry entry = null;
            while ((entry = (CommandLogEntry) objectInputStream.readObject()) != null) {
                commandLogList.add(entry);
            }

        } catch (EOFException e) {
            // do nothing since no commands have been persisted in this log or EOF has reached
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Loaded command log into memory. Command list size: " + commandLogList.size());
    }

    public static boolean doesCommandExist(int index, int term) {
        for (CommandLogEntry entry : commandLogList) {
            if (entry.getTerm() == term && entry.getIndex() == index) {
                return true;
            }
        }
        return false;
    }

    public static CommandLogEntry latestCommittedCommand(CommandLogEntry cle) {
        return null;
    }
    
    public static void insertAndTruncate(CommandLogEntry cle) {
        if (doesCommandExist(cle.getIndex(), cle.getTerm())) {
            // delete list and persist into file
            Iterator<CommandLogEntry> itr = commandLogList.iterator();
            boolean truncateFlag = false;
            int removeAt = commandLogList.size() + 1; // safe guard to keep it outside the list
            for (int i = 0; i < commandLogList.size(); i++) {
                CommandLogEntry entry = commandLogList.get(i);
                if (entry.getTerm() == cle.getTerm() && entry.getIndex() == cle.getIndex()) {
                    truncateFlag = true;
                    removeAt = i;
                    break;
                }

                /*
                Example block if we have to change a value for all remaining items in the iterator.
                if (true) {
                    itr.forEachRemaining(new Consumer<CommandLogEntry>() {
                        @Override
                        public void accept(CommandLogEntry commandLogEntry) {
                            commandLogEntry.forEachTest = "foreach";
                        }
                    });
                }
                */
            }

            // remove command entries from the current entry to the last.
            System.out.println(removeAt);
            commandLogList = new LinkedList<>(commandLogList.subList(0, removeAt)); // sublist returns a list and we need LinkedList cuz of addLast();
//            commandLogList = (LinkedList<CommandLogEntry>) commandLogList.subList(0, removeAt);

            System.out.println(getHistory());
        }

        commandLogList.addLast(cle);
//        persistCommandLog();
    }

    public static String getHistory() {
        StringBuilder sb = new StringBuilder();
        sb.append("CommandLogManager history: \n");
        for (CommandLogEntry entry : commandLogList) {
            sb.append(entry);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static int getSize() {
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
 * <p>
 * Change the commandList into a hashmap or TreeMap with a custom key. Use your own hashes and equals method to use it.
 */
