import java.io.*;

public class DeSerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("msg.ser");

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        MessageSample msg;
        try {
            while ((msg = (MessageSample) objectInputStream.readObject()) != null) {
                System.out.println(msg);
            }
        } catch (EOFException e) {

        }

        objectInputStream.close();
        fileInputStream.close();
    }
}
