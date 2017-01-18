package commandPersistence;

import utilities.LoggerHelper;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class CommandLogManagerTreeMap {

    private static Logger log = LoggerHelper.getLogger(CommandLogManagerTreeMap.class.getName());

    static class LogHistoryKey {
        int index;
        int term;

        public LogHistoryKey(int i, int t) {
            term = t;
            index = i;
        }

        public LogHistoryKey(CommandLogEntry le) {
            term = le.getTerm();
            index = le.getIndex();
        }

        public String toString() {
            return "LogHistoryKey: [index=" + index + ", term=" + term + "]";
        }
    }

    private static TreeMap<Integer, CommandLogEntry> logHistory;

    static {
        logHistory = new TreeMap<>();
//        logHistory = new TreeMap<>((Integer o1, Integer o2) -> o1.index - o2.index);
    }

    public static boolean doesCommandExist(int index, int term) {
        if (!logHistory.containsKey(index)) {
            log.fine("CommandLogManagerTreeMap.doesCommandExist() :: [Index=" + index + ", term=" + term + "] exists? " + false);
            return false;
        }
        CommandLogEntry entry = logHistory.get(index);
        if (entry.getTerm() == term) {
            log.fine("CommandLogManagerTreeMap.doesCommandExist() :: [Index=" + index + ", term=" + term + "] exists? " + true);
            return true;
        } else {
            log.fine("CommandLogManagerTreeMap.doesCommandExist() :: [Index=" + index + ", term=" + term + "] exists? " + false);
            return false;
        }
    }

    public static void insertAndTruncate(CommandLogEntry cle) {
        logHistory.putIfAbsent(new Integer(cle.getIndex()), cle);

    }

    public static String getHistory() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, CommandLogEntry> entry : logHistory.entrySet()) {
            sb.append(entry.getKey() + " ---- ");
            sb.append(entry.getValue() + "\n");
        }
        return sb.toString();
    }
}
