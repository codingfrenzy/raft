package serverNode;

/**
 * Created by prasanthnair on 12/14/16.
 */
public class ServerState {

    private int term;
    private enum currentRole {
        LEADER,
        FOLLOWER,
        CANDIDATE
    };
    private ServerInfo lastTermLeader;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public ServerInfo getLastTermLeader() {
        return lastTermLeader;
    }

    public void setLastTermLeader(ServerInfo lastTermLeader) {
        this.lastTermLeader = lastTermLeader;
    }
}
