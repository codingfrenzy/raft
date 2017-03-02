package serverNode;

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

    protected Map<String, ServerInfo> clusterInfo;
    protected ServerInfo selfInfo;
    protected String logFilePath;
    protected HashMap<String, Integer> stateMachine;

    protected static ServerSocket server;
    protected Socket socket;

    protected ServerState state;

    public ServerBase(ServerInfo si) {
        selfInfo = si;
        clusterInfo = populateClusterInfo();
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

                System.out.println("Server state: " + state);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void processMessage(AppendCommandMessage msg);

    public ServerState getState() {
        return state;
    }

    public void setState(ServerState state) {
        this.state = state;
    }
}
