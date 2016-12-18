package serverNode;


import utilities.ConfigProperties;
import utilities.Constants;

import java.io.IOException;

public class ServerStarter {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Server must have a name as an argument");
            System.exit(0);
        }

        System.out.println(ConfigProperties.getPropertyInt("portOffset.listener.follower"));
        System.out.println(Constants.PORT_OFFSET_LISTENER_FOLLOWER);

        Follower follower = new Follower(args[0]);
        Thread tFol = new Thread(follower);
        tFol.start();

        Leader l = new Leader(args[0]);
        Thread tL = new Thread(l);
        tL.start();

        Candidate c = new Candidate(args[0]);
        Thread tC = new Thread(c);
        tC.start();
    }
}
