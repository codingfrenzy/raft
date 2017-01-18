package commandPersistence;

import serverNode.ServerInfo;

public class CommandLogManagerTester {
    public static void main(String[] args) {
        Command cmd = new Command("test", Command.Options.ADD, 5.0);
        ServerInfo info = new ServerInfo("C");
        CommandLogEntry le1 = new CommandLogEntry(4, 2, info, cmd, false, false);
        CommandLogEntry le2 = new CommandLogEntry(6, 2, info, cmd, false, false);
        CommandLogEntry le3 = new CommandLogEntry(7, 3, info, cmd, false, false);

        CommandLogManagerTreeMap.insertAndTruncate(le1);
        CommandLogManagerTreeMap.insertAndTruncate(le2);
        CommandLogManagerTreeMap.insertAndTruncate(le3);

        System.out.println(CommandLogManagerTreeMap.getHistory());
        System.out.println(CommandLogManagerTreeMap.doesCommandExist(5,2));
    }
}
