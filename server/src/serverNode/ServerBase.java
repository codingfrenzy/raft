package serverNode;

import utilities.ConfigProperties;
import utilities.Constants;

import java.util.HashMap;
import java.util.Set;

import static utilities.Constants.SERVER_CONFIG_PREFIX_STRING;

/**
 * Created by prasanthnair on 12/7/16.
 */
public abstract class ServerBase {

    HashMap<String, ServerInfo> clusterInfo;
    ServerInfo serverInfo;
    String logFilePath;
    HashMap<String, Integer> stateMachine;

    public ServerBase(String name) {
        String serverBaseName = SERVER_CONFIG_PREFIX_STRING;
        serverInfo = new ServerInfo(name, ConfigProperties.getPropertyInt( serverBaseName + name));
        Set<String> serversAtStart = ConfigProperties.getAllPropertyStartingWith(serverBaseName);
        clusterInfo = new HashMap<>(serversAtStart.size());
        for (String server : serversAtStart) {
            String cleanServerName = server.substring(serverBaseName.length());
            clusterInfo.put(cleanServerName, new ServerInfo(cleanServerName, ConfigProperties.getPropertyInt(server)));
        }
        logFilePath = Constants.LOG_FILEPATH_BASE + "" + name;
        stateMachine = new HashMap<>();
    }

}
