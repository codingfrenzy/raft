package commandPersistence;

import serverNode.ServerInfo;
import utilities.LoggerHelper;

import java.util.logging.Logger;

public class CommandLogManager {

    private static Logger log = LoggerHelper.getLogger(CommandLogManager.class.getName());

    /*
    write lock on the file
    acquire write lock on the file before updating with commands
    reading is fine with concurrent

     */

    ServerInfo selfServerInfo;

    public CommandLogManager(ServerInfo si){
        selfServerInfo = si;
    }

    public static boolean doesCommandExist(int index, int term) {
        // get self server name
        // get file name (with server name)
        // read file from bottom
        // read until currentIndex < searchIndex (as there is no point in going back)
        // if currentIndex == searchIndex, check the term
        // return t/f

        CommandLogEntry entry;
        return false;
    }

    public static void insertAndTruncate(CommandLogEntry cle) {

    }

    public static String getHistory() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
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