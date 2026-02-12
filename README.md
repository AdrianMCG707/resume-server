# Resume Server

A Spring Boot REST API built using Java 21 and Spring Boot 4.

This project demonstrates backend fundamentals including:
- REST API development
- JSON serialization
- Embedded Tomcat server
- JPA configuration
- In-memory H2 database (development setup)
- Maven build lifecycle

---

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Web
- Spring Data JPA
- H2 (in-memory database)
- Maven

---

## Current Endpoints

### GET /ping

Returns:

```json
{
  "status": "ok"
}

## How It Works
1. Spring Boot auto-configures the application.
2. Embedded Tomcat runs on port 8080.
3. The DispatcherServlet routes HTTP requests.
4. The PingController handles /ping.
5. The response is serialized to JSON using Jackson.

## Run Locally
mvn spring-boot:run

## Then visit:
http://localhost:8080/ping

## Project Status
✔ Step 1 Complete — Application bootstrapped and REST endpoint verified
⬜ Step 2 — PostgreSQL + Flyway migrations
⬜ Step 3 — Domain models + repository layer
⬜ Step 4 — JWT authentication
⬜ Step 5 — Deployment
