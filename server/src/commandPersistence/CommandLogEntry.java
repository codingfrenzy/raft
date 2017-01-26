package commandPersistence;

import serverNode.ServerInfo;

import java.io.Serializable;

public class CommandLogEntry implements Serializable{
    private ServerInfo leader;
    private int term;
    private int index;
    private Command command;
    private boolean committed;
    private boolean stateMachineExecuted;
    public String forEachTest;

    public CommandLogEntry(String forEachTest) {
        this.forEachTest = forEachTest;
    }

    public CommandLogEntry(int index, int term, ServerInfo leader, Command command, boolean committed, boolean stateMachineExecuted) {
        this.leader = leader;
        this.term = term;
        this.index = index;
        this.command = command;
        this.committed = committed;
        this.stateMachineExecuted = stateMachineExecuted;
    }

    public ServerInfo getLeader() {
        return leader;
    }

    public void setLeader(ServerInfo leader) {
        this.leader = leader;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean isCommitted() {
        return committed;
    }

    public void setCommitted(boolean committed) {
        this.committed = committed;
    }

    public boolean isStateMachineExecuted() {
        return stateMachineExecuted;
    }

    public void setStateMachineExecuted(boolean stateMachineExecuted) {
        this.stateMachineExecuted = stateMachineExecuted;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CommandLogEntry:[index="+index);
        sb.append(", term="+term);
        sb.append(", leader="+leader);
        sb.append(", command="+command);
        sb.append(", committed="+committed);
        sb.append(", stateMachineExecuted="+stateMachineExecuted);
        sb.append(", forEach="+forEachTest);
        sb.append("]");
        return sb.toString();
    }
}
