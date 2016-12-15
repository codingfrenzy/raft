package utilities;

/**
 * utilities.Constants class: Contains all the constants used in the project.
 */
public class Constants {
    public static final String PROPERTIES_FILE_PATH = "config.properties";

    public static final String PORT_OFFSET_PREFIX = "portOffset.listener";

    public static int PORT_OFFSET_APPEND_COMMAND_LISTENER = ConfigProperties.getPropertyInt("portOffset.listener.append");
    public static int PORT_OFFSET_APPEND_ACK_COMMAND_LISTENER = ConfigProperties.getPropertyInt("portOffset.listener.appendAck");
    public static int PORT_OFFSET_REQUEST_VOTE_COMMAND_LISTENER = ConfigProperties.getPropertyInt("portOffset.listener.requestVote");
    public static int PORT_OFFSET_REQUEST_VOTE_ACK_COMMAND_LISTENER = ConfigProperties.getPropertyInt("portOffset.listener.requestVoteAck");

    public static int PORT_OFFSET_LISTENER_LEADER = ConfigProperties.getPropertyInt("portOffset.listener.leader");
    public static int PORT_OFFSET_LISTENER_FOLLOWER = ConfigProperties.getPropertyInt("portOffset.listener.follower");
    public static int PORT_OFFSET_LISTENER_CANDIDATE = ConfigProperties.getPropertyInt("portOffset.listener.candidate");

    public static final String LOG_FILEPATH_BASE = "something_server_";

    public static final String SERVER_CONFIG_PREFIX_STRING = "servers.";

    public static int TERM_TIMEOUT_BASE=ConfigProperties.getPropertyInt("term.timeout.base");
    public static int TERM_TIMEOUT_BUFFER=ConfigProperties.getPropertyInt("term.timeout.buffer");
}
