package cluster;

import utilities.ConfigProperties;
import utilities.Constants;

import java.util.Set;

public class ClusterManager {
    static ServerInfo selfServer;
    static Set<ServerInfo> serversInCluster;

    public static void init(ServerInfo info) {
        selfServer = info;
        Set<String> servers = ConfigProperties.getAllPropertyStartingWith(Constants.SERVER_CONFIG_PREFIX_STRING);
        for (String server : servers) {
            if (server.equals(info)) {
                continue;
            }

            String cleanServerName = server.substring(server.length());
            serversInCluster.add(new ServerInfo(cleanServerName));
        }
    }

    public static Set<ServerInfo> getServersInCluster() {
        return serversInCluster;
    }
}
