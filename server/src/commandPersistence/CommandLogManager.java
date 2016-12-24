package commandPersistence;

import serverNode.ServerInfo;
import utilities.LoggerHelper;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class CommandLogManager {

    private static Logger log = LoggerHelper.getLogger(CommandLogManager.class.getName());

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

    private static TreeMap<LogHistoryKey, CommandLogEntry> logHistory;

    static {
        logHistory = new TreeMap<>((LogHistoryKey o1, LogHistoryKey o2) -> o1.index - o2.index);
    }

    public static boolean doesCommandExist(int index, int term) {
        LogHistoryKey key = new LogHistoryKey(index, term);
        return logHistory.containsKey(key);
    }

    public static void update() {
        Command cmd = new Command("test", Command.Options.ADD, 5.0);
        ServerInfo info = new ServerInfo("C");
        CommandLogEntry le = new CommandLogEntry(4, 2, info, cmd, false, false);
        logHistory.putIfAbsent(new LogHistoryKey(le), le);

        le.setIndex(6);
        le.setTerm(3);
        logHistory.putIfAbsent(new LogHistoryKey(le), le);

        le.setIndex(9);
        le.setTerm(4);
        logHistory.putIfAbsent(new LogHistoryKey(le), le);
    }

    public static String getHistory() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<LogHistoryKey, CommandLogEntry> entry : logHistory.entrySet()) {
            sb.append(entry.getKey() + " ---- ");
            sb.append(entry.getValue() + "\n");
        }
        return sb.toString();
    }
}
