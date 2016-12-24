package serverNode;

import messaging.AppendCommandMessage;
import utilities.Constants;

import java.io.IOException;
import java.net.ServerSocket;

public class Candidate extends ServerBase {
    public Candidate(String name) throws IOException {
        super(name);
        server = new ServerSocket(selfInfo.getServerPortBase() + Constants.PORT_OFFSET_LISTENER_CANDIDATE);
    }

    protected void processMessage(AppendCommandMessage msg) {
        System.out.println("Candidate Received: " + msg);
    }
}
