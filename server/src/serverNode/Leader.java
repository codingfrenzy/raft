package serverNode;

import messaging.Message;
import utilities.Constants;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by prasanthnair on 12/14/16.
 */
public class Leader extends ServerBase {
    public Leader(String name) throws IOException {
        super(name);
        server = new ServerSocket(selfInfo.getServerPortBase() + Constants.PORT_OFFSET_LISTENER_LEADER);
    }

    protected void processMessage(Message msg){
        System.out.println("Leader Received: " + msg);
    }
}
