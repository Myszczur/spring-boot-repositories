# Simple Microservices App

## Description

Contained within this repository is a demonstration project that illustrates a microservices-based application. Its aim is to offer a practical insight into both the architecture and implementation of microservices. The project comprises an API Gateway, Config Server, Discovery Server, and two microservices: Student and School.

## Technologies

- Java Development Kit (JDK) 22 or later or later
- Spring Boot
- Maven

## Running Instructions

1. Clone the repository: `git clone https://github.com/Myszczur/spring-boot-repositories`
2. Navigate to the project directory: `cd simple-micro-services`
3. Run the application using Maven: `mvn spring-boot:run`

## Project Components

### API Gateway
The API Gateway functions as the primary access point for all client requests, overseeing and directing them towards the relevant microservices.

### Config Server
The Config Server centralizes configuration management for all microservices, streamlining application maintenance and ensuring consistency across different environments.

### Discovery Server
The Discovery Server facilitates service registration and discovery, fostering smooth communication between services within the microservices ecosystem.

### Student Microservice
The Student Microservice oversees the management of student-related data and tasks, including the addition, modification, and retrieval of student records.

### School Microservice
The School Microservice administers school-related data and functionalities, encompassing tasks like adding, updating, and retrieving school records.

## Using OpenFeign
This project demonstrates inter-service communication using OpenFeign, a declarative REST client that simplifies service-to-service communication within the microservices ecosystem.