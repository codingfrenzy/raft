package serverNode;

import commandPersistence.CommandLogHelper;
import messaging.Message;
import utilities.ConfigProperties;
import utilities.Constants;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by prasanthnair on 12/7/16.
 */
public abstract class ServerBase implements Runnable {

    HashMap<String, ServerInfo> clusterInfo;
    ServerInfo selfInfo;
    String logFilePath;
    HashMap<String, Integer> stateMachine;

    protected static ServerSocket server;
    protected Socket socket;

    private static CommandLogHelper logHelper;

    static ServerState state;
    static {
        state = new ServerState();
    }

    public ServerBase(String name) {
        String serverBaseName = Constants.SERVER_CONFIG_PREFIX_STRING;
        selfInfo = new ServerInfo(name, ConfigProperties.getPropertyInt(serverBaseName + name));
        Set<String> serversAtStart = ConfigProperties.getAllPropertyStartingWith(serverBaseName);
        clusterInfo = new HashMap<>(serversAtStart.size());
        for (String server : serversAtStart) {
            String cleanServerName = server.substring(serverBaseName.length());
            clusterInfo.put(cleanServerName, new ServerInfo(cleanServerName, ConfigProperties.getPropertyInt(server)));
        }
        logFilePath = Constants.LOG_FILEPATH_BASE + "" + name;
        stateMachine = new HashMap<>();
    }

    @Override
    public void run() {
        System.out.println("Listener active on port: " + server.getLocalPort());
        while (true) {
            try {
                socket = server.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                processMessage((Message) ois.readObject());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void processMessage(Message msg);
}
