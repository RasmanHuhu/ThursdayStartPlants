package dat;

import dat.config.AppConfig;
import dat.ErrorHandling.MyLogger;
import dat.routes.PlantRoutes;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Starter ikke javalin herinde mere, men i AppConfig
        AppConfig config = new AppConfig();

        //laver plantroutes ud af PlantRoutes-klassen
        PlantRoutes plantRoutes = new PlantRoutes();

        //Starter javalin op, gennem config, videre gennem plantroutes, på port 7070
        config.startJavalin(plantRoutes.PlantRoutes(), 7070);

        // ---- MED LOGGER ----

        // Log some information using your logger
        MyLogger.logger.info("Application started successfully.");

        try {
            // Your code that may raise exceptions
        } catch (Exception e) {
            // Log exceptions using your logger
            MyLogger.logger.severe("Exception occurred: " + e.getMessage());
        }
    }
}