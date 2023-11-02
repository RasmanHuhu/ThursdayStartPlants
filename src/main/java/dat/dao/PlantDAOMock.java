package dat.dao;

import dat.ErrorHandling.ApiException;
import dat.dto.PlantDTO;
import jakarta.persistence.EntityManagerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class PlantDAOMock implements IDAO<PlantDTO, String> {

    //DAOMock leger database

    //      Key values:        int      dto
    private Map<Integer, PlantDTO> plantData = new HashMap<>(Map.of(
            1, new PlantDTO(1, "Rose", "Albertine", 400, 199.50),
            2, new PlantDTO(2, "Bush", "Aronia", 200, 169.50),
            3, new PlantDTO(3, "FruitAndBerries", "AromaApple", 350, 399.50),
            4, new PlantDTO(4, "Rhododendron", "Astrid", 40, 269.50),
            5, new PlantDTO(5, "Rose", "TheDarkLady", 100, 199.50)

            //Hvordan populater jeg, uden en populate class?

    ));

    private static PlantDAOMock instance;

    public static PlantDAOMock getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new PlantDAOMock();
        }
        return instance;
    }

    public List<PlantDTO> getAll() {
        try {
            return new ArrayList<>(plantData.values());
        } catch (ApiException e) {
            throw new ApiException(500, "internal server error");
        }
    }


    public PlantDTO getById(int id) {
        try {
            return plantData.get(id);
        } catch (ApiException e) {
            throw new ApiException(500, "internal server error");
        }
    }

    //Før ville co-pilot smamneligne på en funktion der ikke fandtes.
    //Nu tjekker om vi plantType2 er det samme som PlantType i Hashmappet (gennem DTO'en)
    //Virker dog kun hvis public attributes? Problem ifht sikkerhed? Fiks senere

    public List<PlantDTO> getByType(String type) {
        try {
            List<PlantDTO> plantsByType = new ArrayList<>();
            for (PlantDTO plant : plantData.values()) {
                if (plant.getPlantType().equals(type)) {
                    plantsByType.add(plant);
                }
            }
            return plantsByType;
        } catch (ApiException e) {
            throw new ApiException(500, "internal server error");
        }
    }


    public PlantDTO add(PlantDTO plant) {
        try {
            plantData.put(plant.getPlantID(), plant);
            return plant;
        } catch (ApiException e) {
            throw new ApiException(500, "internal server error");
        }
    }

    //#3 TASK - GENERICS AND STREAMS - NYE METODER

    //Return a list of plants with a maximum height of 100 cm using the stream API, filter() and a predicate function:
    //#3.1 Task - Predicate
    //filtrerer på prædikatet som jeg har defineret: planter der er mindre eller lig med 100cm
    public List<PlantDTO> getThingsWithMaxHeight100() {
        try {
            return plantData.values().stream()
                    .filter(plant -> plant.getMaxHeight() <= 100)
                    .collect(Collectors.toList());
        } catch (ApiException e) {
            throw new ApiException(500, "internal server error");
        }
    }

    //Map a  list of PlantDTOs to a list of Strings containing the plant
    //names. Again use the stream API and the map function.
    //#3.2 Task - map, collect
    //mapper en liste af PlantDTOs til en liste af Strings, der indeholder plantenavne
    public List<String> MapThingsNames() {
        try {
            return plantData.values().stream()
                    .map(plant -> plant.getName())
                    .collect(Collectors.toList());
        } catch (ApiException e) {
            throw new ApiException(500, "internal server error");
        }
    }

    //sort a list of PlantDTOs by name using streams, sorted(), and a Comparator.
    //3.3 Task - sorted, collect, comparator,
    //sammenligner to objekter
    public List<PlantDTO> getThingsSortedByName() {
        try {
            return plantData.values().stream()
                    .sorted(Comparator.comparing(PlantDTO::getName))
                    .collect(Collectors.toList());
        } catch (ApiException e) {
            throw new ApiException(500, "internal server error");
        }
    }
}