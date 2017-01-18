package serverNode;

import commandPersistence.CommandLogManager;
import messaging.AppendCommandMessage;
import utilities.Constants;

import java.io.IOException;
import java.net.ServerSocket;

public class Leader extends ServerBase {
    public Leader(ServerInfo serverInfo, CommandLogManager clm) throws IOException {
        super(serverInfo, clm);
        server = new ServerSocket(selfInfo.getServerPortBase() + Constants.PORT_OFFSET_LISTENER_LEADER);
    }

    protected void processMessage(AppendCommandMessage msg) {
        System.out.println("Leader Received: " + msg);
    }
}
