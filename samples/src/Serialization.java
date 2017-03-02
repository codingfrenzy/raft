import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serialization {
    public static void main(String[] args) throws IOException {

        ArrayList<MessageSample> msgs = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            long time = System.currentTimeMillis();
            MessageSample msg = new MessageSample();
            time = i;
            msg.one = time + "-one1";
            msg.two = "two2";
            msgs.add(msg);
        }

        File file = new File("msg.ser");
        if (file.createNewFile()) {
            System.out.println("Creating new file.");
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (MessageSample m : msgs) {
            objectOutputStream.writeObject(m);
        }

        objectOutputStream.close();
        fileOutputStream.close();
    }
}
