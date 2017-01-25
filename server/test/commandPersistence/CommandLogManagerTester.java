package commandPersistence;

import serverNode.ServerInfo;

public class CommandLogManagerTester {
    public static void main(String[] args) {
        Command cmd = new Command("test", Command.Options.ADD, 5.0);
        ServerInfo info = new ServerInfo("C");
        CommandLogManager manager = new CommandLogManager(info);
        CommandLogEntry le1 = new CommandLogEntry(4, 2, info, cmd, false, false);
        CommandLogEntry le2 = new CommandLogEntry(6, 2, info, cmd, false, false);
        CommandLogEntry le3 = new CommandLogEntry(7, 3, info, cmd, false, false);

//        manager.insertAndTruncate(le1);
//        manager.insertAndTruncate(le2);
//        manager.insertAndTruncate(le3);

        System.out.println(manager.getSize());
        System.out.println(manager.getHistory());
        System.out.println(manager.doesCommandExist(5,2));
    }
}
