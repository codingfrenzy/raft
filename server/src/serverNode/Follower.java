package serverNode;

import messaging.AppendCommandMessage;
import utilities.Constants;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by prasanthnair on 12/14/16.
 */
public class Follower extends ServerBase {
    public Follower(String name) throws IOException {
        super(name);
        server = new ServerSocket(selfInfo.getServerPortBase() + Constants.PORT_OFFSET_LISTENER_FOLLOWER);
        System.out.println("server created" + server.getLocalPort());
    }

    protected void processMessage(AppendCommandMessage msg) {
        System.out.println("Follower Received: " + msg);
    }
}
