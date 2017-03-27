package serverNode;

import cluster.ClusterManager;
import cluster.ServerInfo;
import messaging.MessageBase;
import utilities.Constants;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public abstract class ServerBase implements Runnable {

    protected Set<ServerInfo> clusterInfo;
    protected ServerInfo selfInfo;
    protected String logFilePath;
    protected HashMap<String, Integer> stateMachine;

    protected static ServerSocket server;
    protected Socket socket;

    protected ServerState state;

    public ServerBase(ServerInfo si) {
        selfInfo = si;
        clusterInfo = ClusterManager.getServersInCluster();
        logFilePath = Constants.LOG_FILEPATH_BASE + "" + selfInfo.getServerName();
        stateMachine = new HashMap<>();
    }

    synchronized public void updateServerCluster(Set<ServerInfo> cluster){
        clusterInfo = cluster;
    }

    @Override
    public void run() {
        System.out.println("Listener active on port: " + server.getLocalPort());
        while (true) {
            try {
                socket = server.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                processMessage((MessageBase) ois.readObject());

                System.out.println("Server state: " + state);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void processMessage(MessageBase msg);

    public ServerState getState() {
        return state;
    }

    public void setState(ServerState state) {
        this.state = state;
    }
}
