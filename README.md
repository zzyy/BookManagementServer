
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
api page provider by swagger; 

#### local
When the server started on locally, you can access it by `http://localhost:8080/swagger-ui.html`

#### remote
The remote api has been deployed, you can access it through the following link: [BackendApi](http://54.79.220.37:8080/swagger-ui/index.html)


## Build & Run

1. run `docker compose up -d` star postgress database
2. choose a way to start the server 
   - run `./gradlew bootJar` to build jar, then run `java -jar build/libs/BookManagement-0.0.1.jar`
   - open the project by intelJ IDEA, then run

### Build Docker image
1. run `./gradlew bootJar` to build jar
2. run ` docker build -t book_managmen_server .` to build docker image
3. push image or run it

## CI/CD 
Jenkins file has been added in project; 

The Jenkins file build stage includes deployment, which will build a docker image and run it in the Jenkins server. If you want to modify the deployment location, modify the deploy stage

The remote jenkins server can be assessed by following link: [Jenkins CI/CD](http://54.79.220.37:9090/job/BookManagementServer/)

## Related projects
- front project:  [android app](https://github.com/zzyy/BookAppAndroid)