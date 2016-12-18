package utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by prasanthnair on 12/18/16.
 */
public class LoggerHelper {

    private Logger logger;
    private SimpleFormatter sf;
    private FileHandler fh;

    {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT:%1$tL %4$s %2$s %5$s%6$s%n");
        sf = new SimpleFormatter();

        try {
            fh = new FileHandler("application.log", true);
            fh.setLevel(Level.ALL);
            fh.setFormatter(sf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger(String name) {
        LoggerHelper lh = new LoggerHelper();

        lh.logger = Logger.getLogger(name);
        lh.logger.addHandler(lh.fh);
        lh.logger.setLevel(Level.ALL);
        return lh.logger;
    }

}
