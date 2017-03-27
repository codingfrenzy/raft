package serverNode;

import cluster.ServerInfo;
import messaging.MessageBase;
import utilities.Constants;

import java.io.IOException;
import java.net.ServerSocket;

public class Leader extends ServerBase {
    public Leader(ServerInfo serverInfo) throws IOException {
        super(serverInfo);
        server = new ServerSocket(selfInfo.getServerPortBase() + Constants.PORT_OFFSET_LISTENER_LEADER);
    }

    protected void processMessage(MessageBase msg) {
        System.out.println("Leader Received: " + msg);
    }
}
