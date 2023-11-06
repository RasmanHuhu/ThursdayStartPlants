package dat.dao;


import dat.ErrorHandling.ApiException;
import dat.config.AppConfig;
import dat.config.HibernateConfig;
import dat.entities.Plant;
import dat.entities.Reseller;
import dat.routes.PlantRoutes;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.config;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class RestTest {

    private EntityManagerFactory emf;
    private Javalin app;
    private Plant p1;
    private Plant p2;
    private Reseller r1;
    private String baseURL = "http://localhost:7070/api";

    @BeforeAll
    void beforeAll() {
        HibernateConfig.setForTest(true);
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        AppConfig app = new AppConfig();
        PlantRoutes plantRoutes = new PlantRoutes();
        app.startJavalin(plantRoutes.PlantRoutes(), 7070);
    }

    @BeforeEach
    void setup() {
        p1 = new Plant("Rose", "Albertine", 400, 199.50);
        p2 = new Plant("Bush", "Aronia", 200, 169.50);
        Plant p3 = new Plant("FruitAndBerries", "AromaApple", 350, 399.50);
        Plant p4 = new Plant("Rhododendron", "Astrid", 40, 269.50);
        Plant p5 = new Plant("Rose", "The DarkLady", 100, 199.50);
        r1 = new Reseller("Lyngby Plantecenter", "Firskovvej 18", "33212334");
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

    @AfterEach
    void deleteTables() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("TRUNCATE TABLE public.plant RESTART IDENTITY CASCADE").executeUpdate();
            em.createNativeQuery("TRUNCATE TABLE public.reseller RESTART IDENTITY CASCADE").executeUpdate();
            em.getTransaction().commit();
        }
    }

    @AfterAll
    void tearDown() {
        HibernateConfig.setForTest(false);
        app.stop();
    }

    @Test
    void allPlants() {
        // Send a GET request to the API to retrieve a list of plants.
        List<Plant> plants =
                given()
                        .contentType("application/json")
                        .when()
                        .get(baseURL + "/plants")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().body().jsonPath().getList("", Plant.class);

        // Define example plant objects that you expect to be in the response.
        Plant p1 = new Plant(/* Initialize p1 with expected data */);
        Plant p2 = new Plant(/* Initialize p2 with expected data */);

        // Perform assertions on the retrieved data.
        assertThat(p1, isIn(plants));
        assertThat(p2, isIn(plants));
        assertEquals(5, plants.size());

        // In this example:
        //
        //    You send a GET request to the specified API endpoint to retrieve a list of plants.
        //
        //    You perform assertions to check the response status code, extract the JSON response body, and convert it into a list of Plant objects.
        //
        //    You define example Plant objects (p1 and p2) that you expect to be present in the response.
        //
        //    You perform assertions to check if p1 and p2 are in the list of plants, and you verify that the size of the list is as expected.
    }

    @Test
    void getByID() {
        given()
                .contentType("application/json")
                .when()
                .get(baseURL + "/plants/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(1));

        // In this example:
        //
        //    You send a GET request to the specified API endpoint for a specific plant by its ID (baseURL + "/plants/" + plantId).
        //
        //    You perform assertions to check the response status code and to ensure that the "id" field in the response body matches the expected plant ID.
    }

    @Test
    void getByIDNonExist() {
        given()
                .contentType("application/json")
                .when()
                .get(baseURL + "/plants/25")
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Plant with ID: 25 does not exist."));
    }

    @Test
    void getByType() {
        List<Plant> plants =
                given()
                        .contentType("application/json")
                        .when()
                        .get(baseURL + "/plants/type/rose")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().body().jsonPath().getList("", Plant.class);
        assertEquals(2, plants.size());
        assertThat(p1, isIn(plants));

        // In this test:
        //
        //    You send a GET request to the specified API endpoint for a plant with a non-existent ID.
        //
        //    You perform assertions to check the response status code (404) and to ensure that the "message" field in the response body matches the expected error message, which includes the non-existent plant ID.

    }

    @Test
    void getByTypeNonExist() {
        given()
                .contentType("application/json")
                .when()
                .get(baseURL + "/plants/type/tree")
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Plants with type: tree does not exist."));

        // In this test:
        //
        //    You send a GET request to the specified API endpoint for plants with a non-existent type.
        //
        //    You perform assertions to check the response status code (404) and to ensure that the "message" field in the response body matches the expected error message, which includes the non-existent plant type.
        //
        //Make sure to replace "http://your-api-base-url" with the actual base URL of the API you are testing, and set nonExistentType to a plant type that doesn't exist in your API.
    }

    @Test
    void add() {
        given()
                .contentType("application/json")
                .body("{\"name\": \"OliveTree\", \"type\": \"Tree\", \"maxHeight\": 1000, \"price\": 1499.50}")
                .when()
                .post(baseURL + "/plants")
                .then()
                .assertThat()
                .statusCode(201)
                .body("id", equalTo(6));

        // In this test:
        //
        //    You send a POST request to the specified API endpoint for adding a new plant.
        //
        //    You provide the request body with the data for the new plant in JSON format.
        //
        //    You perform assertions to check the response status code (201) and to ensure that the "id" field in the response is not null, indicating that the plant was successfully added and has been assigned an ID.
        //
        //Make sure to replace "http://your-api-base-url" with the actual base URL of the API you are testing, and provide the requestBody with the data for the new plant you want to add.
    }

    @Test
    void addPlantToReseller() {
        Reseller reseller =
                given()
                        .contentType("application/json")
                        .when()
                        .put(baseURL + "/plants/1/1")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().body().jsonPath().getObject("", Reseller.class);
        assertThat(reseller.getPlants().size(), equalTo(1));
        assertThat(reseller.getPlants(), contains(p1));

        // In this test:
        //
        //    You send a PUT request to the specified API endpoint to add a plant to a reseller. You should replace plantId and resellerId with the actual IDs of the plant and reseller you want to associate.
        //
        //    You receive the updated reseller in the response.
        //
        //    You perform assertions to check that the reseller now has one plant (getPlants().size() == 1) and that it contains the expected plant (contains(p1)). Make sure to initialize p1 with the expected plant data before the test.
        //
        //Ensure that you replace "http://your-api-base-url" with the actual base URL of the API you are testing and use the correct plant and reseller IDs in the test.
    }

    @Test
    void delete() throws ApiException {
        PlantCenterDAO plantCenterDAO = new PlantCenterDAO();
        plantCenterDAO.addPlantToReseller(1, 1);
        given()
                .contentType("application/json")
                .when()
                .delete(baseURL + "/plants/1")
                .then()
                .assertThat()
                .statusCode(204);

        given()
                .contentType("application/json")
                .when()
                .delete(baseURL + "/plants/1")
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("The plant you are trying to delete does not exist, please ensure you are using the correct id."));


        //This test appears to be testing the process of deleting a plant through an API and verifying that the plant is correctly removed from the system. Let me explain what happens in this test step by step:
        //
        //    PlantCenterDAO Initialization: The test starts by creating a new instance of the PlantCenterDAO class.
        //
        //    Add Plant to Reseller: It adds a plant to a reseller using the plantCenterDAO.addPlantToReseller(1, 1) method. This is an action taken before the actual delete operation to set up the test scenario.
        //
        //    First DELETE Request: The test sends an HTTP DELETE request to the specified URL (baseURL + "/plants/1") to delete a plant with ID 1.
        //
        //    First DELETE Response Check: It then checks the response using RestAssured to ensure that the HTTP status code is 204, indicating a successful deletion. This verifies that the plant with ID 1 was deleted.
        //
        //    Second DELETE Request: The test sends another DELETE request to delete the same plant with ID 1, even though it has already been deleted in the previous step.
        //
        //    Second DELETE Response Check: The test checks the response of the second DELETE request and expects an HTTP status code of 404, indicating that the plant with ID 1 does not exist anymore.
        //
        //    Second DELETE Response Body Check: Additionally, the test checks the response body to ensure that the error message is as expected, indicating that the plant no longer exists.
        //
        //In summary, this test covers scenarios where you attempt to delete a plant,
    }
}