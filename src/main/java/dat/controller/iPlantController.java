package dat.controller;

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

    //#Put (update metode)
    public Handler update(int id);

    public Handler delete(int id);

    //#5 - ikke i brug, men del af CRUD
    // public Handler deletePlant();



}
