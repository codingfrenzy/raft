package serverNode;

import commandPersistence.CommandLogManager;
import messaging.AppendCommandMessage;
import utilities.ConfigProperties;
import utilities.Constants;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class ServerBase implements Runnable {

    Map<String, ServerInfo> clusterInfo;
    ServerInfo selfInfo;
    String logFilePath;
    HashMap<String, Integer> stateMachine;

    protected static ServerSocket server;
    protected Socket socket;

    private static CommandLogManager commandLogManager;

    static ServerState state;

    static {
        state = new ServerState();
    }

    public ServerBase(ServerInfo si, CommandLogManager clm) {
        selfInfo = si;
        clusterInfo = populateClusterInfo();
        commandLogManager = clm;
        logFilePath = Constants.LOG_FILEPATH_BASE + "" + selfInfo.getServerName();
        stateMachine = new HashMap<>();
    }

    private Map<String, ServerInfo> populateClusterInfo(){
        Map<String, ServerInfo> clusterInfo;
        String serverBaseName = Constants.SERVER_CONFIG_PREFIX_STRING;
        Set<String> serversAtStart = ConfigProperties.getAllPropertyStartingWith(serverBaseName);
        clusterInfo = new HashMap<>(serversAtStart.size());
        for (String server : serversAtStart) {
            String cleanServerName = server.substring(serverBaseName.length());
            clusterInfo.put(cleanServerName, new ServerInfo(cleanServerName));
        }
        return clusterInfo;
    }

    @Override
    public void run() {
        System.out.println("Listener active on port: " + server.getLocalPort());
        while (true) {
            try {
                socket = server.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                processMessage((AppendCommandMessage) ois.readObject());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void processMessage(AppendCommandMessage msg);
}
