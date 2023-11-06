package dat.dao;

import dat.config.HibernateConfig;
import dat.dto.PlantDTO;
import dat.entities.Plant;
import dat.entities.Reseller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

//#Task 4.4 Implement a PlantDAO class that implements the IDAO interface. The PlantDAO class should add
//3 extra methods:
public class PlantCenterDAO implements iDAO<PlantDTO, String, Plant> {

    //Lille søde plante objekt
    Plant plant = new Plant();

    //Husk
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


    @Override
    public List<Plant> getAll() {
        //det try'en er her, er en try-with-resources, som automatisk lukker EntityManageren ned efter den er færdig med at bruge den
        try (EntityManager em = emf.createEntityManager()) {
            //Returner min liste af planter gennem en Query, som er en JPQL (Java Persistence Query Language)
            return em.createQuery("SELECT p FROM Plant p", Plant.class).getResultList();
        }
    }

    @Override
    public Plant getById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            //finder mine planter på id
            return em.find(Plant.class, id);
        }
    }

    @Override
    public List<Plant> getByType(String type) {
        try (EntityManager em = emf.createEntityManager()) {
            //finder mine planter på type
            return em.createQuery("SELECT p FROM Plant p WHERE p.type = :type", Plant.class).setParameter("type", type).getResultList();
        }
    }

    @Override
    public Plant add(PlantDTO plantDTO) {
        Plant plant = new Plant(plantDTO.getType(), plantDTO.getName(), plantDTO.getMaxHeight(), plantDTO.getPrice());
        try (EntityManager em = emf.createEntityManager()) {
            //starter en transaction
            em.getTransaction().begin();
            //tilføjer min plante med merge, så den opdaterer så der er noget i databasen (blanding af persist og update)
            em.merge(plant);
            //gemmer min plante
            em.getTransaction().commit();
            //returnerer min plante :)
            return plant;
        }
    }

    /// ---- 4 extra methods ----:

    //Find er den eneste som må ligge FØR em.begin() - fordi den ikke ændrer noget i databasen
    //delete-route
    public Plant delete(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            //finder min plante på id
            Plant plant = em.find(Plant.class, id);
            //starter en transaction
            em.getTransaction().begin();
            //fjerner min plante
            em.remove(plant);
            //gemmer min plante
            em.getTransaction().commit();
            //returnerer min plante :)
            return plant;
        }
    }

    //Updater plantobjects
    public Plant update(int id, Plant plantobj) {
        try (EntityManager em = emf.createEntityManager()) {
            Plant plant = em.find(Plant.class, id);
            em.getTransaction().begin();
            plant.setName(plantobj.getName());
            plant.setType(plantobj.getType());
            plant.setMaxHeight(plantobj.getMaxHeight());
            plant.setPrice(plantobj.getPrice());
            em.merge(plant);
            em.getTransaction().commit();
            return plant;
        }
    }

    public Reseller addPlantToReseller(int resellerId, int plantId) {
        try (EntityManager em = emf.createEntityManager()) {
            //finder min reseller på id
            Reseller reseller = em.find(Reseller.class, resellerId);
            //finder min plante på id
            Plant plant = em.find(Plant.class, plantId);
            //starter en transaction
            em.getTransaction().begin();
            //tilføjer min plante til min reseller
            reseller.getPlants().add(plant);
            //så merger vi det ind i vores database - fra plant til reseller.
            em.merge(reseller);
            //gemmer min plante
            em.getTransaction().commit();
            //returnerer min reseller :)
            return reseller;
        }
    }

    public List<Plant> getPlantsByReseller(int resellerId) {
        try (EntityManager em = emf.createEntityManager()) {
            //finder min reseller på id
            Reseller reseller = em.find(Reseller.class, resellerId);
            //finder min resellers planter
            List<Plant> plants = reseller.getPlants();
            //starter en transaction
            em.getTransaction().begin();
            //gemmer min resellers planter
            em.getTransaction().commit();
            //returnerer min resellers planter
            return plants;
        }
    }
}

