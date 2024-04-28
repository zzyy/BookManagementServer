
## Requirements
 - JDK: 17+
 - Docker

## Architecture
- Language: Java
- Framework: Spring Boot with Spring Data JPA; 
- Database: postgres
- Code quality: checkstyle, Junit,PMD
- Build: Gradle-kotlin, docker, jenkins

### APIs:
api page provider by swagger; when server star on local, you can access by `http://localhost:8080/swagger-ui.html`


## Build & Run
### Local
1. run `docker compose up -d` star postgress database
2. choose a way to open the server 
   - run `./gradlew bootJar` to build jar, then run `java -jar build/libs/BookManagement-0.0.1.jar`
   - open the project by intelJ IDEA, then run

### Server
1. run `./gradlew bootJar` to build jar
2. run ` docker build -t book_managmen_server .` to build docker image
3. push image 

## CI/CD 
Jenkinsfile has been added in project; the Jenkinsfile build stage contain deploy, it will build a docker image, and run it in jenkins sever