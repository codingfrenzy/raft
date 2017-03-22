package messaging;

import commandPersistence.Command;
import utilities.Constants.CommandCommitStatus;

import java.io.Serializable;

/**
 * Created by prasanthnair on 12/7/16.
 */
public class AppendCommandMessage extends MessageBase implements Serializable {
    private int nextIndex;
    private CommandCommitStatus commitStatus;
    private Command command;

    // constructor

    public String toString() {
        return originatorServer + "-" + term;
    }
}
