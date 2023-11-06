package dat.controller;

import dat.ErrorHandling.ApiException;
import dat.dao.PlantCenterDAO;
import dat.dto.PlantDTO;
import dat.entities.Plant;
import io.javalin.http.Handler;

import java.lang.reflect.Type;

//JPA-delen (Funktionel programmering, streams, lambda udtryk, etc.)
//Bruger DAO'en til at implementere Controller metoderne

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
            Plant plant = plantCenterDAO.add(ctx.bodyAsClass(PlantDTO.class));
            ctx.status(201);
        };
    }

    // ----- DE EKSTRA -----



    public Handler update(int id) {
        return ctx -> {
            Plant plant = ctx.bodyAsClass(Plant.class);
            plantCenterDAO.update(id, plant);
        };
    }

    @Override
    public Handler delete(int id) {
        return ctx -> {
            plantCenterDAO.delete(id);
            ctx.status(202); //deleted
        };
    }
    //Når de her er lavet, så retter vi i routes i Main
}

