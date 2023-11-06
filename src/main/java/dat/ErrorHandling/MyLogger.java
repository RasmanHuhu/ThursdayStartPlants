package dat.ErrorHandling;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class MyLogger {
    // Create a private static Logger instance for MyLogger
    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

    // Configure the logger to write log messages to "app.log" file
    static {
        try {
            // Create a FileHandler to write log messages to a file called "app.log"
            FileHandler fileHandler = new FileHandler("app.log", true);
            // Set the log message formatter to SimpleFormatter
            fileHandler.setFormatter(new SimpleFormatter());
            // Add the FileHandler to the logger's handlers
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            // Handle IOException if it occurs and print the stack trace
            e.printStackTrace();
        }
    }
}
