package serverNode;

import utilities.Constants;

public class ServerStateManager implements Runnable {
    private ServerState state = null;
    private long lastHeartBeatTime;
    private long lastElectionResponseTime;

    //last time leader contacted
    // lower limit - term
    // upper limit = 2 X lower
    //

    /*
    should termTime be same as lower time limit?
    pros?
    cons?
     */

    public ServerStateManager(ServerState ss) {
        state = ss;
        lastHeartBeatTime = System.currentTimeMillis();
        lastElectionResponseTime = System.currentTimeMillis();
    }

    public synchronized void updateHeartBeatTime(){
        lastHeartBeatTime = System.currentTimeMillis();
    }

    public void run() {

        while (true) {
            long currentTime = System.currentTimeMillis();

            /*
             if last heartbeat was within the heartbeatTimeout limit,
             or if current server is undergoing election
             cluster is working as expected -> sleep and retry
              */
            if (lastHeartBeatTime > (currentTime - (Constants.HEARTBEAT_TIMEOUT_SECONDS * 1000)) && state.getCurrentRole() != ServerState.Role.CANDIDATE) {
                try {
                    Thread.sleep(Constants.HEARTBEAT_TIMEOUT_SECONDS);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /*
            todo
            promote to candidate
                candidate sends request to all servers
                start another thread whcih times the election timeout.
            onMsg keeps count
                if majority reached, then promote to leader
                else keep listening

            election timer thread looks at electionTimeout && server state.
                if not candidate, kill self thread

             */
            /*
            Reaching here means leader is missing,
            wait for election timeout to get over, then check if voted for a term higher than current term
            if not, then promote and RequestVote
             */
        }


        // sleep for lower limit
        // randomize between lower and upper
        // sleep for interval
        // check term
        // check currentleader
        // check pastleader
        // check last heartbeat within timer

        /*
        if leader
        if term time passed,
            step down state so that new requests are not accepted.

         */

        /*
        if follower do:

        if last contacted is > lower time
        then its ok cuz the leader had contacted this server for something
         */
    }
}
