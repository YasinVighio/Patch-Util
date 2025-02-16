package org.patcher.utility;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    public static final Logger logger = Logger.getLogger(AppLogger.class.getName());
    private static final String LOG_FILE_NAME = "app.log";

    public static void initLogger() throws Exception{
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_NAME, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (Exception e){
            System.err.println("Failed to init logger: "+e.getMessage());
            throw e;
        }
    }

    public static void logSevere(String msg, Exception e){
        logger.log(Level.SEVERE, msg, e);
    }

    public static void logInfo(String msg){
        logger.log(Level.INFO, msg);
    }

}
