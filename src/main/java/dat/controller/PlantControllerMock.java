package dat.controller;

import dat.ErrorHandling.ApiException;
import dat.dao.PlantDAOMock;
import dat.dto.PlantDTO;
import io.javalin.http.Handler;


public class PlantControllerMock implements iPlantController {

    PlantDAOMock plantDAOMock = new PlantDAOMock();

    //#1 - hiver dataen ind fra instansen af min PlantDAOMock - "plantDAOMock" (linje 13)
    //Fordi vi bruger Handler, fremfor void, så behøver vi ikke tilføje "throws x exception", selvom vi wrapper i try/catch
    @Override
    public Handler getAll() {
        return ctx -> {
            try {
                ctx.json(plantDAOMock.getAll());
                ctx.status(202);
            } catch (Exception e) {
                throw new ApiException(500, "internal server error");
            }
        };
    }


    public Handler getByType(String type) {
        return ctx -> {
            try {
                ctx.json(plantDAOMock.getByType(type));
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
            ctx.json(plantDAOMock.getById(id));
        };
    }


    //#3 - del af CRUD, men ikke i brug
    /*
    public Handler deletePlant() {
        return ctx -> {
            String id = ctx.pathParam("id");
            String jsonString = "{'message': 'Delete plant with ID: " + id + "'}";
            ctx.json(jsonString);
        };
    }
*/

    //Behøver ikke (PlantDTO plant), som opgaven beder mig om, fordi jeg allerede deklarerer den i linje 61.
    //Interface kontrakt passer.
    public Handler add() {
        return ctx -> {
            PlantDTO newPlant = ctx.bodyAsClass(PlantDTO.class);
            ctx.status(201);
        };
    }


    //Updating the id
    public Handler update(int id) {
        return ctx -> {
            PlantDTO plant = ctx.bodyAsClass(PlantDTO.class);
            plantDAOMock.update(id, plant);
            ctx.status(200); //updated
        };
    }

    public Handler delete(int id) {
        return ctx -> {
            plantDAOMock.delete(id);
            ctx.status(204); //deleted
        };
    }
}