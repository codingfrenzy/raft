import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Sample class to listen to a TCP port and display the messages.
 */
public class TCPListenerSample {

    private static ServerSocket server;

    private static int port = 15213;

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {

        server = new ServerSocket(port);
        System.out.println("Waiting for client request");

        while (true) {
            Socket socket = server.accept();
            System.out.println("Client connected");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            MessageSample messageSample = (MessageSample) ois.readObject();
            System.out.println("MessageSample Received: " + messageSample);
            Thread.sleep(5 * 1000);

            /*
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            MessageSample m = new MessageSample();
            m.one = "one1";
            m.two = "two2";
            oos.writeObject(m);
            */

            if (messageSample.one.equals("exit")) {
                break;
            }
            socket.close();
        }
//        ois.close();
        System.out.println("Shutting down Socket server!!");
        server.close();
    }
}
