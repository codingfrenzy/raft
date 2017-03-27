package serverNode;

import cluster.ServerInfo;
import messaging.MessageBase;
import messaging.RequestVoteMessage;
import utilities.Constants;

import java.io.IOException;
import java.net.ServerSocket;

public class Candidate extends ServerBase {
    public Candidate(ServerInfo serverInfo) throws IOException {
        super(serverInfo);
        server = new ServerSocket(selfInfo.getServerPortBase() + Constants.PORT_OFFSET_LISTENER_CANDIDATE);
    }

    protected void processMessage(MessageBase msg) {
        System.out.println("Candidate Received: " + msg);

        RequestVoteMessage rvm = (RequestVoteMessage) msg;
    }

    public void sentVoteRequests(){
        for(ServerInfo server : clusterInfo){
            // make request vote msg
            // send it to all
        }
    }
}
