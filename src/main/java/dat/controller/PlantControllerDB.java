package dat.controller;

import dat.ErrorHandling.ApiException;
import dat.dao.PlantCenterDAO;
import dat.entities.Plant;
import io.javalin.http.Handler;

public class PlantControllerDB implements iPlantController {

    //Lav instans af PlantCenterDAO

    PlantCenterDAO plantCenterDAO = new PlantCenterDAO();

    public Handler getAll() {
        return ctx -> {
            try {
                ctx.json(plantCenterDAO.getAll());
                ctx.status(202);
            } catch (Exception e) {
                throw new ApiException(500, "internal server error");
            }
        };
    }


    public Handler getByType(String type) {
        return ctx -> {
            try {
                ctx.json(plantCenterDAO.getByType(type));
                ctx.status(202);
            } catch (Exception e) {
                throw new ApiException(500, "internal server error");
            }
        };
    }

    //#2

    public Handler getById(int id) {
        return ctx -> {
            ctx.pathParam("id");
            ctx.json(plantCenterDAO.getById(id));
        };
    }


    //Behøver ikke (PlantDTO plant), som opgaven beder mig om, fordi jeg allerede deklarerer den i linje 61.
    //Interface kontrakt passer.
    public Handler add() {
        return ctx -> {
            Plant plant = ctx.bodyAsClass(Plant.class);
            ctx.status(201);
        };
    }

    //Når de her er lavet, så retter vi i routes i Main
}

