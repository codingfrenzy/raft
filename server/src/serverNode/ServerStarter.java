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

        String serverName = args[0];
        ServerInfo serverInfo = new ServerInfo(serverName);
        Constants.selfServerName = serverName;

        Follower follower = new Follower(serverInfo);
        Thread tFol = new Thread(follower);
        tFol.start();

        Leader l = new Leader(serverInfo);
        Thread tL = new Thread(l);
        tL.start();

        Candidate c = new Candidate(serverInfo);
        Thread tC = new Thread(c);
        tC.start();
    }
}
