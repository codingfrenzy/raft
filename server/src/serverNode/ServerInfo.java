package serverNode;

import utilities.ConfigProperties;

public class ServerInfo {
    private String serverName;
    private int serverPortBase;

    /*
    state information per server:
    index+term
    lastIndex
     */

    public ServerInfo(String name) {
        serverName = name;
        serverPortBase = ConfigProperties.getServerPort(name);
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getServerPortBase() {
        return serverPortBase;
    }

    public void setServerPortBase(int serverPortBase) {
        this.serverPortBase = serverPortBase;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ServerInfo: [");
        sb.append("name=" + serverName);
        sb.append(", portBase=" + serverPortBase);
        sb.append("]");
        return sb.toString();
    }
}
