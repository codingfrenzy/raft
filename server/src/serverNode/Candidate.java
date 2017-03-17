package serverNode;

import commandPersistence.CommandLogManager;
import messaging.AppendCommandMessage;
import utilities.Constants;

import java.io.IOException;
import java.net.ServerSocket;

public class Candidate extends ServerBase {
    public Candidate(ServerInfo serverInfo) throws IOException {
        super(serverInfo);
        server = new ServerSocket(selfInfo.getServerPortBase() + Constants.PORT_OFFSET_LISTENER_CANDIDATE);
    }

    protected void processMessage(Object objMsg) {
        System.out.println("Candidate Received: " + objMsg);
    }
}
