package utilities;

import java.io.Serializable;

/**
 * utilities.Constants class: Contains all the constants used in the project.
 */
public class Constants implements Serializable {
    public static final int HEARTBEAT_TIMEOUT_SECONDS = 5;
    public static final int ELECTION_TIMEOUT_LOWER_MILLISECONDS = 500;
    public static final int ELECTION_TIMEOUT_UPPER_MMILLISECONDS = 1000;

    public static final String PROPERTIES_FILE_PATH = "config.properties";

    public static final String PORT_OFFSET_PREFIX = "portOffset.listener";

    public static int PORT_OFFSET_LISTENER_LEADER = ConfigProperties.getPropertyInt("portOffset.listener.leader");
    public static int PORT_OFFSET_LISTENER_FOLLOWER = ConfigProperties.getPropertyInt("portOffset.listener.follower");
    public static int PORT_OFFSET_LISTENER_CANDIDATE = ConfigProperties.getPropertyInt("portOffset.listener.candidate");

    public static final String LOG_FILEPATH_BASE = "something_server_";

    public static final String SERVER_CONFIG_PREFIX_STRING = "servers.";

    public static int TERM_TIMEOUT_BASE = ConfigProperties.getPropertyInt("term.timeout.base");
    public static int TERM_TIMEOUT_BUFFER = ConfigProperties.getPropertyInt("term.timeout.buffer");

    public enum RequestVoteStages {
        RREQUEST_VOTE,
        ACCEPT_VOTE,
        REJECTE_VOTE
    }

    public enum CommandCommitStatus {
        BEFORE_COMMIT, // initial test for consensus
        TO_COMMIT, // leader has executed in state machine and sends to followers to commit
        COMMITTED, // command is executed in state machine and ACKS to leader
    }

    public enum MessageType {
        CLIENT_REQUEST, // Leader
        APPEND_COMMAND, // Follower
        APPEND_COMMAND_ACK, // Leader
        REQUEST_VOTE, // Follower
        REQUEST_VOTE_ACK // Candidate
    }

    public static String selfServerName;
}
