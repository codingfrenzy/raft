package messaging;

import java.io.Serializable;

/**
 * Created by prasanthnair on 12/7/16.
 */
public class AppendCommandAckMessage extends MessageBase implements Serializable {
    private int nextIndex;
    private boolean commit;
    private boolean executedStateMachine;

    public String originator;
    public int term;


    public String toString() {
        return originator + "-" + term;
    }
}
