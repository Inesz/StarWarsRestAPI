# StarWarsRestAPI

The application takes ​query criteria ​ and queries following services:
- https://swapi.co/api/films/
- https://swapi.co/api/people/
- https://swapi.co/api/planets/
to obtain list of films in which appeared characters who contains given CHARACTER_PHRASE ​
in their name and whose homeworld planet is ​PLANET_NAME.

The application queries API with user input and stores transformed result in database.

## Swagger documentation
/swagger-ui.html

## Rest API:
|functionality|method|url|
|---|---|---|
|add/update| PUT | /report|
|get report| GET | /report/{report_id}|
|get all reports| GET | /report|
|delete report| DELETE | /report/{report_id}|
|delete all reports| DELETE | /report|

### Request body:
{
"query_criteria_character_phrase": "PHRASE",
"query_criteria_planet_name": "NAME"
}

### Response body:
{[{
“report_id”: “{report_id},
“query_criteria_character_phrase”: “CHARACTER_PHRASE”,
“query_criteria_planet_name”: “PLANET_NAME”,
“result”: [{
    “film_id”: “FILM_ID”,
    “film_name”: “FILM_NAME”,
    “character_id”: “CHARACTER_ID”,
    “character_name”: “CHARACTER_NAME”,
    “planet_id”: “PLANET_ID”,
    “planet_name”: “PLANET_NAME” }]
}]}


## Technologies used:
- Java JDK 11
- Spring Boot
- Spring 5 WebFlux
- Spring 5 WebClient
- Maven 3
- Hibernate 5
- JPA
- Javadoc
- Swagger
- JUnit 5