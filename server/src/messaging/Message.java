package messaging;

import java.io.Serializable;

/**
 * Created by prasanthnair on 12/7/16.
 */
public class Message implements Serializable {
    /*
    origin_servername
    command type enum
    command
    index
    term
    lastIndex
    saveToFile? Y/N
    commit? Y/N
    stateMachine? Y/N

    command_ACK?
     */

    public String originator;
    public int term;
    public enum MESSAGE_TYPE {
        CLIENT_REQUEST, // Leader
        APPEND_COMMAND, // Follower
        APPEND_COMMAND_ACK, // Leader
        REQUEST_VOTE, // Follower
        REQUEST_VOTE_ACK // Candidate
    }

    public String toString(){
        return originator + "-" + term;
    }
}
