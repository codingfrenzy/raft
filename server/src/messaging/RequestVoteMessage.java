package messaging;

import java.io.Serializable;

/**
 * Created by prasanthnair on 12/7/16.
 */
public class AppendCommandMessage extends MessageBase implements Serializable {
    private RequestVoteStages voteStages;

    public AppendCommandMessage(int lastLogIndex, int lastLogTerm, ServerInfo candidateServer, int term, RequestVoteStages voteStages) {
        this.lastLogIndex = lastLogIndex;
        this.lastLogTerm = lastLogTerm;
        this.candidateServer = candidateServer;
        this.term = term;
        this.voteStages = voteStages;
    }


    public String toString() {
        return originator + "-" + term;
    }
}
