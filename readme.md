# JEGY

Jegy (Hungarian for "ticket") is a fast, simple, developer-friendly ticketing system.

It has a Spring Boot (Java) back end, a React front end, and a MySQL database powering it.

## Development

Ensure you have Java 11, npm (or yarn), and a MySQL instance running.

Steps for development: 

To start the database, run: 

```
cd database
docker kill jegy-mysql; docker rm jegy-mysql;
.\dockerBuild.ps1; .\dockerRun.ps1;
```

To start the backend, run: 

`mvn spring-boot:run`

To start the frontend, cd into `app` and run:
 
`yarn start` (or `npm start`)
