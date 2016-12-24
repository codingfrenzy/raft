import java.io.Serializable;

public class MessageSample implements Serializable {
    public String one;
    public String two;

    public String toString(){
        return one + "-" + two;
    }
}
