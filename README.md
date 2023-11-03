# Eksamen

## Task #1

## Task #2.1

200 = Ok
201 = Created
400 = Bad request
404 = Not found
500 = Internal server error

| HTTP method | REST Ressource            | Exceptions and status(es) |
|-------------|---------------------------|---------------------------|
| GET         | `/api/plants`             | 200, 500                  |
| GET         | `/api/plants/{id}`        | 200, 404, 500             |
| GET         | `/api/plants/type/{type}` | 200, 404, 500             |
| POST        | `/api/plants`             | 201, 400, 500             |

## Task #3.4

What programming paradigm are we following?

I'd say we're following the functional programming paradigm, because through streams we're using predicates/mappers to 
filter and map the data and thus are using functional programming through anonymous functions.

## Task #4

Når vi når til opgaven, hvor vi skal lave en PlantControllerDB, så skal vi reroute vores endpoints til denne fremfor PlantDAOMock'en. 
Fordi vi ikke bruger en mock DB, men derimod en rigtig gennem JPA.
Vi tager metoderne fra PlantController og propper dem over i PlantControllerDB, og retter fra "PlantDAOMock" til instansen af PlantControllerDB = "plantControllerDB".
Nede i add metoden, så laves den dog lidt om, da det ikke længere er DTO'en der er i brug.

Postgres:

1. Docker åbnes (OG HELT KLAR, tager tid :))
2. Åben database ude til højre, postgres, skriv localhost som navn, skriv postgres + postgres 
som brugernavn og kode og test connection.
3. "create database exam" når den er connected (ctrl + k for at køre)
4. Properties -> Schemas -> Fjern alle databaser og tilføj den ønskede
5. Ret til i HibernateConfig ved AnnotatedClasses 
6. Ret til i HibernateConfig ved DATABASE-navnet i linje 32, postgres, 
så den hedder det du ønsker at kalde din database. (f.eks. exam?)
7. Ret 
8. Vi fylder databasen op med de ting vi har i vores PlantCenterDB, så gennem Main og Javalin, så kan vi fylde op gennem Dev.http og Post metoden

## Task #5

## Task #6