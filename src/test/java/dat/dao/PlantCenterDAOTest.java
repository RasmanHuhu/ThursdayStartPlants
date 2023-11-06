package dat.dao;

import dat.ErrorHandling.ApiException;
import dat.config.HibernateConfig;
import dat.dto.PlantDTO;
import dat.entities.Plant;
import dat.entities.Reseller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlantCenterDAOTest {

    private EntityManagerFactory emf;

    private PlantCenterDAO plantCenterDAO;
    private Plant p1;

    @BeforeAll
    void setupClass() {
        HibernateConfig.setForTest(true);
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        plantCenterDAO = new PlantCenterDAO();
    }

    @BeforeEach
    void setup() {
        p1 = new Plant("Rose", "Albertine", 400, 199.50);
        Plant p2 = new Plant("Bush", "Aronia", 200, 169.50);
        Plant p3 = new Plant("FruitAndBerries", "AromaApple", 350, 399.50);
        Plant p4 = new Plant("Rhododendron", "Astrid", 40, 269.50);
        Plant p5 = new Plant("Rose", "The DarkLady", 100, 199.50);
        Reseller r1 = new Reseller("Lyngby Plantecenter", "Firskovvej 18", "33212334");
        Reseller r2 = new Reseller("Glostrup Planter", "Tværvej 35", "32233232");
        try (EntityManager em = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);
            em.persist(p5);
            em.persist(r1);
            em.persist(r2);
            em.getTransaction().commit();
        }
    }


    //#5.2 - Sletter min database efter hver test og sørger for et rent test-setup
    @AfterEach
    void afterEach() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("TRUNCATE TABLE Plant RESTART IDENTITY CASCADE").executeUpdate();
            em.createNativeQuery("TRUNCATE TABLE Reseller RESTART IDENTITY CASCADE").executeUpdate();
            em.getTransaction().commit();
        }
    }

    @AfterAll
    void tearDown() {
        HibernateConfig.setForTest(false);
    }

    @Test
    void getAll() {
        List<Plant> allPlants = plantCenterDAO.getAll();
        assertEquals(5, allPlants.size());
    }

    @Test
    void getByID() { //Kan man throws ApiException { her?

        /* Plant found = plantCenterDAO.getById(1);
        Plant expected = new Plant( "Rose", "Albertine", 400, 199.50);
        assertEquals(expected, found); */

        // Get a plant with ID 1 from the database using the getById method.
        Plant found = plantCenterDAO.getById(1);

// Extract the ID of the found plant.
        int actual = found.getId();

// Define the expected ID.
        int expected = 1;

// Assert that the actual ID matches the expected ID.
        assertEquals(expected, actual);
       //? -> assertThrows(ApiException.class, () -> plantCenterDAO.getById(?));
    }

    //Finder hvor manger "Roser" der findes på size
    @Test
    void getByType(){
        List<Plant> plantsByType = plantCenterDAO.getByType("Rose");
        assertEquals(2, plantsByType.size());
    }

    @Test
    void add() {
        // Get the count of all plants before adding a new one.
        List<Plant> allPlantsBefore = plantCenterDAO.getAll();

        // Add a new plant using the `add` method.
        Plant newPlant = plantCenterDAO.add(new PlantDTO(1, "Busk", "Hr. Busk", 400, 899.50));

        // Get the count of all plants after adding the new plant.
        List<Plant> allPlantsAfter = plantCenterDAO.getAll();

        // Ensure that the newPlant is not null.
        assertNotNull(newPlant);

        // Verify that the ID of the newPlant is incremented as expected.
        assertEquals(allPlantsBefore.size() + 1, allPlantsAfter.size());
    }

    @Test
    void delete() {
        // Get the count of all plants before deleting.
        List<Plant> allPlantsBefore = plantCenterDAO.getAll();

        // Assuming that you have an existing plant with a known ID, delete it.
        int plantIdToDelete = 1; // Replace with the actual ID of the plant you want to delete.
        plantCenterDAO.deletePlant(plantIdToDelete);

        // Get the count of all plants after deleting.
        List<Plant> allPlantsAfter = plantCenterDAO.getAll();

        // Verify that the plant with the specified ID has been deleted.
        // Check that the count of plants after deleting is one less than before.
        assertEquals(allPlantsBefore.size() - 1, allPlantsAfter.size());
    }
}