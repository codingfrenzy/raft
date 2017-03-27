package serverNode;


import cluster.ServerInfo;
import utilities.ConfigProperties;
import utilities.Constants;

import java.io.IOException;
import java.util.ArrayList;

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

        Leader listener = new Leader(serverInfo);
        listener.setState(state);

        Candidate candidate = new Candidate(serverInfo);
        candidate.setState(state);

        ServerStateManager timer = new ServerStateManager(state, candidate);

        ArrayList<Runnable> listOfThreads = new ArrayList<>();
        listOfThreads.add(follower);
        listOfThreads.add(listener);
        listOfThreads.add(candidate);
        listOfThreads.add(timer);

        for(Runnable sb : listOfThreads){
            Thread t = new Thread(sb);
            t.start();
        }
    }

    // todo get last persisted term info so that if a server resumes it will know what term it was.
    private static int getLastTerm() {
        return 1;
    }
}
