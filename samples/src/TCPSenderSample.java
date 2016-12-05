import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Sample class to listen to a TCP port and display the messages.
 */
public class TCPSenderSample {

    private static ServerSocket server;

    private static int port = 15213;

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        System.out.println("Connecting to server");
        Socket socket = new Socket("localhost", port);
        System.out.println("Connected:" + socket.getLocalAddress());

        while (true) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            MessageSample m = new MessageSample();
            m.one = "one";
            m.two = "two";

            oos.writeObject(m);

            System.out.println("Waiting");
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("after Waiting");
//            String message = (String) ois.readObject();
//            System.out.println("MessageSample Received from server: " +  ois.readObject());

//            ois.close();
            oos.close();
            socket.close();
            //terminate the server if client sends exit request
//            if (message.equalsIgnoreCase("exit")) {
                break;
//            }
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }
}
