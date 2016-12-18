package messaging;

import serverNode.ServerInfo;
import utilities.Constants;

/**
 * Created by prasanthnair on 12/7/16.
 */
public abstract class MessageBase {

    protected ServerInfo originator;
    protected Constants.MessageType type;
    protected int index;
    protected int term;

}
