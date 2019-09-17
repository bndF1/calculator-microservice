[![CodeFactor](https://www.codefactor.io/repository/github/bndf1/calculator-microservice/badge)](https://www.codefactor.io/repository/github/bndf1/calculator-microservice)
# Welcome to microservice calculator! :rocket:

Hi! This is the **calculator microservice example**. In this example two endpoints are exposed, in order to represent this basic operations:  

    Add
     
and
 
	Subtract  

In order to perform or test these simple operations, we can use the attached postman collection, once the microservice has been started.

# Technology stack used

The technology stack used for this example, including libraries and tools are:

 - Spring Boot 2.17 and Spring 5
-   Maven
-   Project Lombok
-   IntelliJ IDEA 2019.2 (Community Edition)
-   OpenJDK version 8
-   JUnit 5

Moreover some dependencies have been added in order to facilitate the development lifecycle and improve test abstraction (EasyRandom and JaCoCo for test reports):

     <dependency>  
    	 <groupId>org.jeasy</groupId>  
    	 <artifactId>easy-random-core</artifactId>  
    	 <version>4.0.0</version>  
    </dependency>


## Methodolgy used

For this example I've used TDD as development methodology, and can be checked at commits: 
[ee1cdb9](https://github.com/bndF1/calculator-microservice/commit/ee1cdb9f792fe9cd8206b0b1682881aab673589b), [11a619c](https://github.com/bndF1/calculator-microservice/commit/11a619c7acd6a6efb0ecd7742e6e23beecf0ee35) and [683805c](https://github.com/bndF1/calculator-microservice/commit/683805c4938f9a0c5c49dbd28fea3fa2159107ee) although there are more examples.

Also would be noticiable to know that Github projects have been used for a basic representation of the tasks done.

## Other instructions

Running the application (development mode):

	mvn spring-boot:run

Runing unit tests: 

	mvn test  
	
Running integration tests: 
	
	mvn verify  
  
Creating the jar: 
	
	mvn clean package

Running the generated .jar
	
	java -jar target/calculator.jar 

Tests results with JaCoCo are aviable at
	
	/target/site/jacoco

Please note that I have focused on bussiness layer and api rest layer with integration test, that means that tests for DTOs have not been done or tests for default generated class to run the application has not been tested.
