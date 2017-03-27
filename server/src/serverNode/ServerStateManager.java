package serverNode;

import utilities.Constants;
import serverNode.ServerState.Role;

public class ServerStateManager implements Runnable {
    private ServerState state = null;

    private Candidate candidateServer;

    //last time leader contacted
    // lower limit - term
    // upper limit = 2 X lower
    //

    /*
    should termTime be same as lower time limit?
    pros?
    cons?
     */

    public ServerStateManager(ServerState ss, Candidate candidate) {
        state = ss;
        candidateServer = candidate;
    }

    public void run() {

        while (true) {
            long currentTime = System.currentTimeMillis();

            boolean stateChangeRequired = true;

            synchronized (state) {
                /*
                 if last heartbeat was within the heartbeatTimeout limit,
                 or if current server is undergoing election
                 cluster is working as expected -> sleep and retry
                  */

                if (state.getLastHeartBeatTime() > (currentTime - (Constants.HEARTBEAT_TIMEOUT_SECONDS * 1000)) && state.getCurrentRole() != Role.CANDIDATE) {
                    stateChangeRequired = false;
                }
            }

            if (stateChangeRequired) {
                try {
                    Thread.sleep(Constants.HEARTBEAT_TIMEOUT_SECONDS);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // have to promote to Candidate and start the election process because there was no communication during the timers.
            synchronized (state) {
                state.updateElectionTimer();
                state.updateHeartBeatTime();
                state.setCurrentRole(Role.CANDIDATE);
            }

            candidateServer.sentVoteRequests();
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
