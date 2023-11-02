package dat.controller;

import dat.dao.PlantDAOMock;
import dat.dto.PlantDTO;
import io.javalin.http.Handler;

public interface iPlantController {


    //#1
    public Handler getAllPlants();

    //#2
    public Handler getPlantByType(String plantType);

    //#3
    public Handler getPlantById(int id);

    //#4
    public Handler addPlant();

    //#5 - ikke i brug, men del af CRUD
    // public Handler deletePlant();



}
