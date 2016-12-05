import java.io.Serializable;

/**
 * Created by prasanthnair on 12/2/16.
 */
public class MessageSample implements Serializable {
    public String one;
    public String two;

    public String toString(){
        return one + "-" + two;
    }
}
