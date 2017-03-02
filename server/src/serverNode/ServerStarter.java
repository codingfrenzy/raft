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

        ServerState state = new ServerState(getLastTerm());

        Follower follower = new Follower(serverInfo);
        follower.setState(state);

        Leader l = new Leader(serverInfo);
        l.setState(state);

        Candidate c = new Candidate(serverInfo);
        c.setState(state);

        Thread tFol = new Thread(follower);
        tFol.start();

        Thread tL = new Thread(l);
        tL.start();

        Thread tC = new Thread(c);
        tC.start();
    }

    // todo get last persisted term info so that if a server resumes it will know what term it was.
    private static int getLastTerm() {
        return 1;
    }
}
