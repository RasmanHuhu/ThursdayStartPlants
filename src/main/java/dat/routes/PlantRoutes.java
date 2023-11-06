package dat.routes;

import dat.controller.PlantController;
import dat.controller.PlantControllerDB;
import dat.controller.iPlantController;
import dat.dto.PlantDTO;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class PlantRoutes {

    //Denne skal ikke være static!
    // private final PlantController plantController = new PlantController();

    //Ny tech:

    iPlantController iplantController = new PlantControllerDB();

    //De metoder som vi kan se efter både get/post her, er dem jeg får fra controlleren
    //This is the juice, som viser os output i browseren/dev.html
    //Mine routes
    ///Lav denne protected, så den er sikret, men åben
    public EndpointGroup PlantRoutes() {

        return () -> path("/plants", () -> {

            //Hiver alle planter
            get("/", iplantController.getAll());

            //Hiver planterne på deres id
            get("/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                iplantController.getById(id).handle(ctx);

            });

            //Hiver planterne på deres type
            get("/type/{type}", ctx -> {
                String type = ctx.pathParam("type");
                iplantController.getByType(type).handle(ctx);

            });
            //Endpoint til at add'e en plante
            post("/", iplantController.add());

            //            Add a PUT endpoint for updating a plant by ID
            //            put("/{id}", ctx -> {
            //            int id = Integer.parseInt(ctx.pathParam("id"));
            //            Implement the logic to update the plant by ID using iplantController
            //            For example: iplantController.updateById(id, ctx);

        });
    }
}
