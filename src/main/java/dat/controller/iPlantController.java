package dat.controller;

import dat.dao.PlantDAOMock;
import dat.dto.PlantDTO;
import io.javalin.http.Handler;

public interface iPlantController {


    //#1
    public Handler getAll();

    //#2
    public Handler getByType(String type);

    //#3
    public Handler getById(int id);

    //#4
    public Handler add();

    //#5 - ikke i brug, men del af CRUD
    // public Handler deletePlant();



}
