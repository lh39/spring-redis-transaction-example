# Spring Boot - Redis Transactional Example

This repository contains code to demonstrate Spring Boot transaction behavior when working with a redis instance.    
It also demonstrates how to create self-instantiated domain components with Spring __autowiring__ within the application context.


## Prerequisites
Locally running redis instance with default configuration

## Run the application
```
mvn spring-boot:run
```

## Redis
### Configuration
* see [RedisConfiguration](src/main/java/de/lh39/springredistransactionexample/configuration/RedisConfiguration.java), with default redis configuration and an embedded database for enabling transaction management 
### Usage
* see `@Transactional` behavior through [SpringRedisTransactionExampleApplication](src/main/java/de/lh39/springredistransactionexample/SpringRedisTransactionExampleApplication.java), where the example is provided
* see [TestComponent](src/main/java/de/lh39/springredistransactionexample/component/TestComponent.java), where the calls are marked with the transactional annotation

## Autowiring self-instantiated domain component 
* see [TestComponentFactory](src/main/java/de/lh39/springredistransactionexample/component/TestComponentFactory.java), which is responsible for providing assisted autowiring, by offering a factory method to create autowired objects with custom parameters
* see [TestComponent](src/main/java/de/lh39/springredistransactionexample/component/TestComponent.java), which is the instantiated object
