package messaging;

import serverNode.ServerInfo;
import utilities.Constants;

/**
 * Created by prasanthnair on 12/7/16.
 */
public abstract class MessageBase {

    protected ServerInfo originatorServer;
    protected Constants.MessageType type;
    protected int lastLogIndex;
    protected int lastLogTerm;
    protected int term;

}
