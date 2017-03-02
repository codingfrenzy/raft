package commandPersistence;

import serverNode.ServerInfo;
import utilities.Constants;

public class CommandLogManagerTester {
    public static void main(String[] args) {
        Constants.selfServerName = "C";
        Command cmd = new Command("test", Command.Options.ADD, 5);
        ServerInfo info = new ServerInfo(Constants.selfServerName);
        CommandLogEntry le1 = new CommandLogEntry(4, 2, info, cmd, false, false);
        CommandLogEntry le2 = new CommandLogEntry(6, 2, info, cmd, false, false);
        CommandLogEntry le3 = new CommandLogEntry(7, 3, info, cmd, false, false);

//        CommandLogManager.insertAndTruncate(le1);
//        CommandLogManager.insertAndTruncate(le2);
//        CommandLogManager.insertAndTruncate(le3);

        System.out.println(CommandLogManager.getHistory());
        Command cmd2 = new Command("test2", Command.Options.SUBTRACT, 2);
        CommandLogEntry le4 = new CommandLogEntry(6, 2, info, cmd2, false, false);
        CommandLogManager.insertAndTruncate(le4);

        System.out.println(CommandLogManager.getSize());
        System.out.println(CommandLogManager.getHistory());
        System.out.println(CommandLogManager.doesCommandExist(5, 2));
        System.out.println(CommandLogManager.doesCommandExist(6, 2));
    }
}
