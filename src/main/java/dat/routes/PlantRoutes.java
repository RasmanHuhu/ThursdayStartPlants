package dat.routes;

import dat.controller.PlantController;
import dat.dto.PlantDTO;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class PlantRoutes {

    //Denne skal ikke være static!
    private final PlantController plantController = new PlantController();


    //De metoder som vi kan se efter både get/post her, er dem jeg får fra controlleren
    //This is the juice, som viser os output i browseren/dev.html
    //Mine routes
    ///Lav denne protected, så den er sikret, men åben
    public EndpointGroup PlantRoutes(){
        return() ->
                path("/plants", () -> {
                    get("/", plantController.getAll());



                    get("/{id}", ctx -> {
                                int id = Integer.parseInt(ctx.pathParam("id"));
                                plantController.getById(id).handle(ctx);

                            });

                        get("/type/{type}", ctx -> {
                                    String type = ctx.pathParam("type");
                                    plantController.getByType(type).handle(ctx);

                                });

                            post("/", plantController.add());

                        });
                    }
                }
