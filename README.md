# devops-practices-spring-boot
This repository contains a simple Spring Boot application incrementally evolved adopting industry-standard practices of DevOps applications.

## Prerequisites
- [Java](https://www.java.com/en/) 17 or higher
- [Maven](https://maven.apache.org/) 3.6 or higher
- [Docker](https://www.docker.com/) 25 or higher

## Getting started
You can run the application either using Docker or a JAR file.
If you want to run the application using Docker, you can follow the instructions below.
1. Clone this repository by running `git clone https://github.com/MattiaDellOca/devops-practices-spring-boot.git`
2. Navigate to the project root directory by running `cd devops-practices-spring-boot`
3. Create a docker image by running `docker build -t devops-practices-spring-boot .`
4. Run the docker image by running `docker run -p 8080:8080 devops-practices-spring-boot`

If you prefer to run the application using a JAR file, you can follow the instructions below.
1. Clone this repository by running `git clone https://github.com/MattiaDellOca/devops-practices-spring-boot.git`
2. Navigate to the project root directory by running `cd devops-practices-spring-boot`
3. Create a JAR file by running `mvn clean package`
4. Run the application by running `java -jar target/devops-practices-spring-boot-0.0.1-SNAPSHOT.jar`