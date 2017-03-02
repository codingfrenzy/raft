package serverNode;

public class ServerState {

    public enum Role {
        LEADER,
        FOLLOWER,
        CANDIDATE;
    }

    private int term;
    private ServerInfo lastTermLeader;
    private ServerInfo currentTermLeader;
    private Role currentRole;

    public ServerState(int term) {
        this.term = term;
        lastTermLeader = null;
        currentTermLeader = null;
    }

    public Role getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public ServerInfo getCurrentTermLeader() {
        return currentTermLeader;
    }

    public void setCurrentTermLeader(ServerInfo currentTermLeader) {
        this.currentTermLeader = currentTermLeader;
    }

    public ServerInfo getLastTermLeader() {
        return lastTermLeader;
    }

    public void setLastTermLeader(ServerInfo lastTermLeader) {
        this.lastTermLeader = lastTermLeader;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ServerState:[");
        sb.append("term=" + term);
        sb.append(", currentRole=" + currentRole);
        sb.append(", lastTermLeader=" + lastTermLeader);
        sb.append(", currentTermLeader=" + currentTermLeader);
        sb.append("]");
        return sb.toString();
    }
}
