import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Sample class to listen to a TCP port and display the messages.
 */
public class TCPSenderSample {

    private static int port = 15213;

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        double rand = Math.random();
        for (int i = 0; i < 2; i++) {

            System.out.println("Connecting to server");
            Socket socket = new Socket("localhost", port);
            System.out.println("Connected:" + socket.getLocalAddress());

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            MessageSample m = new MessageSample();
            m.one = "one" + rand;
            m.two = "two";
            oos.writeObject(m);

            oos.close();
            socket.close();
        }
        System.out.println("Shutting down Socket server!!");
    }
}
