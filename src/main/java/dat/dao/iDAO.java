package dat.dao;

import java.util.List;

//GENERICS
//OsteBolle = T = hvad som helst
//TomatSmør = K,
//Kan fylde flere parametre på
public interface iDAO<OsteBolle, TomatSmør> {

   List<OsteBolle> getAll();

   OsteBolle getById(int id);

   List<OsteBolle> getByType(String type);

   OsteBolle add(OsteBolle add);

    //STREAMS-METODER? bør ikke være en del af din IDAO - de er jo blot en del af plantdaomock'en
    //List<OsteBolle> getThingsWithMaxHeight100();
    //List<TomatSmør> MapThingsNames();
    //List<OsteBolle> getThingsSortedByName();

}
