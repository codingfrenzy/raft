package commandPersistence;

import serverNode.ServerInfo;

/**
 * Created by prasanthnair on 12/14/16.
 */
public class LogEntry {
    ServerInfo leader;
    int term;
    int index;
    Command command;
    boolean committed;
    boolean stateMachineExecuted;

    public LogEntry(ServerInfo leader, int term, int index, Command command, boolean committed, boolean stateMachineExecuted) {
        this.leader = leader;
        this.term = term;
        this.index = index;
        this.command = command;
        this.committed = committed;
        this.stateMachineExecuted = stateMachineExecuted;
    }
}
