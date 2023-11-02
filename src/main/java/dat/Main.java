package dat;

import dat.config.AppConfig;
import dat.controller.PlantController;
import dat.dto.PlantDTO;
import dat.routes.PlantRoutes;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

import java.io.IOException;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //Starter ikke javalin herinde mere, men i AppConfig
        AppConfig config = new AppConfig();

        //laver plantroutes ud af PlantRoutes-klassen
        PlantRoutes plantRoutes = new PlantRoutes();

        //Starter javalin op, gennem config, videre gennem plantroutes, på port 7070
        config.startJavalin(plantRoutes.PlantRoutes(), 7070);

    }
}