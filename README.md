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

--
How It Works
Spring Boot auto-configures the application.
Embedded Tomcat runs on port 8080.
The DispatcherServlet routes HTTP requests.
The PingController handles /ping.
The response is serialized to JSON using Jackson.
Run Locally
mvn spring-boot:run
Then visit:
http://localhost:8080/ping
Project Status
✔ Step 1 Complete — Application bootstrapped and REST endpoint verified
⬜ Step 2 — PostgreSQL + Flyway migrations
⬜ Step 3 — Domain models + repository layer
⬜ Step 4 — JWT authentication
⬜ Step 5 — Deployment
