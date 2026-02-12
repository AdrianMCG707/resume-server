# Resume Server

A Spring Boot REST API built using Java 21 and Spring Boot 4.

This project demonstrates backend engineering fundamentals including REST API development, JSON serialization, embedded server configuration, and database integration.

---

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Web (REST)
- Spring Data JPA
- H2 (in-memory database for development)
- Maven

---

## Architecture Overview

This project follows a layered backend structure:

- **Controller Layer** – Handles HTTP requests
- **Service Layer** – (Planned) Business logic
- **Repository Layer** – (Planned) Database interaction
- **Database** – H2 (development), PostgreSQL (planned)

Spring Boot auto-configures the application and runs an embedded Tomcat server on port 8080.

---

## Current Endpoints

### GET /ping

Returns:

```json
{
  "status": "ok"
}
