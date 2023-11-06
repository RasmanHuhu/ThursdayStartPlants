package dat.routes;

import dat.controller.PlantControllerDB;
import dat.controller.iPlantController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class PlantRoutes {

    //Denne skal ikke være static!
    // private final PlantController plantController = new PlantController();

    //Ny tech:

    //Her tager den begge sider - Mock og DB - og håndterer begge controllers på én gang
    iPlantController iplantController = new PlantControllerDB();

    //De metoder som vi kan se efter både get/post her, er dem jeg får fra controlleren
    //This is the juice, som viser os output i browseren/dev.html
    //Mine routes
    ///Lav denne protected, så den er sikret, men åben
    public EndpointGroup PlantRoutes() {

        return () -> path("/plants", () -> {

            //Get all plants
            get("/", iplantController.getAll());

            //Getting a plant by Id
            get("/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                iplantController.getById(id).handle(ctx);

            });

            //Getting a plant by type
            get("/type/{type}", ctx -> {
                String type = ctx.pathParam("type");
                iplantController.getByType(type).handle(ctx);

            });
            //Adding a plant
            post("/", iplantController.add());


            ///---- EKSTRA ----
            //Updating my ID
            put("/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                iplantController.update(1).handle(ctx);
            });

            //Implement the logic to update the plant by ID using iplantController
            //For example: iplantController.updateById(id, ctx);

            delete("/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                iplantController.delete(id).handle(ctx);
            });

        });
    }
}
