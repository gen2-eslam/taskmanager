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

## Installation & Setup

### Quick Start with Docker Compose (Recommended)

The simplest way to run the application is using Docker Compose. This automatically sets up both the Spring Boot application and PostgreSQL database with a single command.

**Prerequisites:**
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed

**Steps:**

1. **Clone the repository** (if you haven't already):
   ```bash
   git clone <repository-url>
   cd taskmanager
   ```

2. **Run with Docker Compose:**
   ```bash
   docker-compose up
   ```

   This will:
   - Pull the pre-built application image from Docker Hub (`geneslam123/taskmanager`)
   - Start a PostgreSQL database container
   - Automatically connect the application to the database

3. **Access the application:**
   - Application: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui/index.html`

**To stop the application:**
```bash
docker-compose down
```

### Alternative: Build from Source

If you want to build the Docker image locally instead of using the pre-built image:

1. **Uncomment the build line** in `docker-compose.yml`:
   ```yaml
   spring_boot_app:
     container_name: taskmanager
     build: .  # Uncomment this line
     # image: geneslam123/taskmanager  # Comment out this line
   ```

2. **Run with build:**
   ```bash
   docker-compose up --build
   ```

### Local Development Setup

For local development without Docker:

1. **Start PostgreSQL** (using Docker):
   ```bash
   docker-compose up postgres -d
   ```

2. **Update database connection** in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

## API Documentation
The application provides interactive API documentation via Swagger UI.

- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
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