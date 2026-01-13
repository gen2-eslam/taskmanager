# Task Manager Application

## Overview
A simpler Task Manager API built with Java Spring Boot. This application allows users to manage task lists and tasks, providing a robust backend for task tracking applications.

## Tech Stack
- **Language:** Java 21
- **Framework:** Spring Boot 3
- **Database:** PostgreSQL
- **Containerization:** Docker & Docker Compose
- **Build Tool:** Maven

## Prerequisites
Before running the application, ensure you have the following installed:
- [Java 21 JDK](https://adoptium.net/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (includes Docker Compose)
- [Maven](https://maven.apache.org/) (optional, wrapper included)

## Getting Started

### 1. Run with Docker (Recommended)
The easiest way to run the application is using Docker Compose, which sets up both the application and the PostgreSQL database.

```bash
docker-compose up --build
```

The application will be available at `http://localhost:8080`.

### 2. Local Development
If you prefer to run the application locally without Docker for the app itself:

1.  **Start the Database:**
    You still need a PostgreSQL database. You can use Docker for just the DB:
    ```bash
    docker-compose up db -d
    ```

2.  **Run the Application:**
    Use the Maven wrapper to start the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```

## API Documentation
The application provides interactive API documentation via Swagger UI.

- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI Json:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

### Main Resources
- **Task Lists**: Manage collections of tasks (Create, Read, Update, Delete).
- **Tasks**: Manage individual tasks within a list (Create, Read, Update, Delete).

## Project Structure
```
src/main/java/com/example/taskmanager/
├── controllers/      # REST Controllers (API Endpoints)
├── domain/
│   ├── dto/          # Data Transfer Objects
│   └── entities/     # JPA Entities (Database Models)
├── mappers/          # Object Mappers (Entity <-> DTO)
├── services/         # Business Logic Layer
└── TaskmanagerApplication.java # Entry Point
```

## Configuration
Database connection details are configured in `docker-compose.yml` and `application.properties` (or environment variables).

**Default Credentials:**
- **Database:** `postgres`
- **Username:** `postgres`
- **Password:** `fares`