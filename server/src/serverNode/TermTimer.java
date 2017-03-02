package serverNode;

public class TermTimer implements Runnable {
    ServerState state = null;
    //last time leader contacted
    // lower limit - term
    // upper limit = 2 X lower
    //

    /*
    should termTime be same as lower time limit?
    pros?
    cons?

     */
    public void run() {
        // sleep for lower limit
        // randomize between lower and upper
        // sleep for interval
        // check term
        // check currentleader
        // check pastleader

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
