package dat.dao;

import java.util.List;

//GENERICS
//OsteBolle = T = hvad som helst
//TomatSmør = K,
//Kan fylde flere parametre på
public interface iDAO<T, K, G> {
   // iDAO<PlantDTO, String, Plant>
   // --------T--------K-------G----

   List<G> getAll();

   G getById(int id);

   List<G> getByType(String type);

   G add(T add);

    //STREAMS-METODER? bør ikke være en del af din IDAO - de er jo blot en del af plantdaomock'en
    //List<OsteBolle> getThingsWithMaxHeight100();
    //List<TomatSmør> MapThingsNames();
    //List<OsteBolle> getThingsSortedByName();

}
