package messaging;

import commandPersistence.Command;

import java.io.Serializable;

/**
 * Created by prasanthnair on 12/7/16.
 */
public class AppendCommandMessage extends MessageBase implements Serializable {
    private int nextIndex;
    private boolean committed;
    private boolean stateMachineExecuted;

    public String originator;
    public int term;
    Command command;


    public String toString() {
        return originator + "-" + term;
    }
}
