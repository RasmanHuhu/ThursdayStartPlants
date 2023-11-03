package dat.dao;

import java.util.List;

//GENERICS
//OsteBolle = DTO (hehe)
//TomatSmør = T
//Kan fylde flere parametre på
public interface IDAO <OsteBolle, TomatSmør> {

   List<OsteBolle> getAll();

   OsteBolle getById(int id);

   List<OsteBolle> getByType(String type);

   OsteBolle add(OsteBolle add);

   //STREAMS-METODER?

    List<OsteBolle> getThingsWithMaxHeight100();

    List<TomatSmør> MapThingsNames();

    List<OsteBolle> getThingsSortedByName();










}
