# JEGY

Jegy (Hungarian for "ticket") is a fast, simple, developer-friendly ticketing system.

It has a Spring Boot (Java) back end, a React front end, and a MySQL database powering it.

## Development

Ensure you have Java 11, npm (or yarn), and a MySQL instance running.

Steps for development: 

- Check out the project
- cd into the directory (or open it in your IDE)
- Rename `application.properties.example` to `application.properties`.
- Fill out the required attributes in your `application.properties` (just DB info).
- Run the `schema.sql` on your database.

To start the backend, run: 

`mvn spring-boot:run`

To start the frontend, cd into `app` and run:
 
`yarn start` (or `npm start`)
