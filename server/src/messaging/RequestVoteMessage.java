package messaging;

import serverNode.ServerInfo;
import utilities.Constants.RequestVoteStages;

import java.io.Serializable;

/**
 * Created by prasanthnair on 12/7/16.
 */
public class RequestVoteMessage extends MessageBase implements Serializable {
    private RequestVoteStages voteStages;
    private ServerInfo candidateServer;

    public RequestVoteMessage(int lastLogIndex, int lastLogTerm, ServerInfo candidateServer, int term, RequestVoteStages voteStages) {
        this.lastLogIndex = lastLogIndex;
        this.lastLogTerm = lastLogTerm;
        this.candidateServer = candidateServer;
        this.term = term;
        this.voteStages = voteStages;
    }


    public String toString() {
        return originatorServer + "-" + term;
    }
}
