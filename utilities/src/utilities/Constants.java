package utilities;

/**
 * utilities.Constants class: Contains all the constants used in the project.
 */
public class Constants {
    public static final String PROPERTIES_FILE_PATH = "config.properties";

    public static final int PORT_OFFSET_APPEND_COMMAND_LISTENER = ConfigProperties.getPropertyInt("portOffset.listener.append");
    public static final int PORT_OFFSET_APPEND_ACK_COMMAND_LISTENER = ConfigProperties.getPropertyInt("portOffset.listener.appendAck");
    public static final int PORT_OFFSET_REQUEST_VOTE_COMMAND_LISTENER = ConfigProperties.getPropertyInt("portOffset.listener.requestVote");
    public static final int PORT_OFFSET_REQUEST_VOTE_ACK_COMMAND_LISTENER = ConfigProperties.getPropertyInt("portOffset.listener.requestVoteAck");

    public static final String LOG_FILEPATH_BASE = "something_server_";

    public static final String SERVER_CONFIG_PREFIX_STRING = "servers.";
}
