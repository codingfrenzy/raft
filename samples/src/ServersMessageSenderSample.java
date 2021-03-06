import messaging.AppendCommandMessage;
import utilities.ConfigProperties;
import utilities.Constants;
import utilities.LoggerHelper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Sample class to listen to a TCP port and display the messages.
 */
public class ServersMessageSenderSample {

    private static int port = 15000;
    private static Logger log = LoggerHelper.getLogger(ServersMessageSenderSample.class.getName());

    public static void main(String args[]) {

//        double rand = Math.random();
        int portOffset = 0;
        for (String prop : ConfigProperties.getAllPropertyStartingWith(Constants.PORT_OFFSET_PREFIX)) {
            try {
                System.out.println("Connecting to server");
                portOffset = ConfigProperties.getPropertyInt(prop);
                Socket socket = new Socket("localhost", port + portOffset);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                AppendCommandMessage m = new AppendCommandMessage();
                m.originator = "sender_" + prop;
                m.term = 4;
                oos.writeObject(m);

                oos.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("Could not connect: " + portOffset);
//                e.printStackTrace();
            }
        }
        log.info("samppllee!!!");
        log.severe("samppllee!!!");
        log.fine("samppllee!!!");
        log.warning("samppllee!!!");
        System.out.println("Shutting down Socket server!!");
    }
}
