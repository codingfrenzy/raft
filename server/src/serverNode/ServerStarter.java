package serverNode;


public class ServerStarter extends ServerBase {

    ServerStarter(String name) {
        super(name);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Server must have a name as an argument");
            System.exit(0);
        }

        ServerStarter server = new ServerStarter(args[0]);
    }
}
