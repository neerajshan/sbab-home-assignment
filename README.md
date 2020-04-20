SBAB Home Assignment Project
--
The task is to implement an API for potential clients. The API will have minimum 2 endpoints.
One end-point should return the top 10 bus lines in Stockholm area with most bus stops on
their route. The other endpoint should return a list of every bus stop of the bus line
provided. There is no requirement on how the bus stops are sorted.
To accomplish the task please use the Trafiklab’s open API (https://www.trafiklab.se). You
can find more information about the specific API at the documentation page:
http://www.trafiklab.se/api/sl-hallplatser-och-linjer-2.
You can register your own account at Trafiklab and obtain an API key.
The data should be automatically gathered from API for each run of the application.
---
# Solution Language & Framework choice
- Java 11
- Spring boot Project
- Maven build tool for project and running integration test
- Spring Data JPA for persistence layer
- H2 in memory database 
- Swagger 3 for Api Documentation 
- RestController for Apis
- Health Check endpoints  
- BDD Test with Cucumber 
---
#How to Build, run integration test & run project
- Build project & run integration test `mvn clean install`
- Run the app with `mvn spring-boot:run` then browse to http://localhost:8080.
- When the app is running, the in-memory DB can be inspected at http://localhost:8080/h2-console 
- This is, of course, a proof of concept and should not be deployed publicly.
