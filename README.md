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

```

This endpoint verifies that:

- The server is running
- The embedded Tomcat instance is active
- The Spring DispatcherServlet is routing correctly
- JSON serialization is functioning

---

## How It Works

1. Spring Boot initializes the application context.
2. Embedded Tomcat starts on port 8080.
3. The `DispatcherServlet` routes incoming HTTP requests.
4. The `PingController` handles the `/ping` endpoint.
5. Jackson serializes the Java response object into JSON.

---

## Run Locally

```bash
mvn spring-boot:run
```

## Then visit:
```bash
http://localhost:8080/ping
```

## Project Status
✔ Step 1 — Application bootstrapped and REST endpoint verified
⬜ Step 2 — PostgreSQL integration + Flyway migrations
⬜ Step 3 — Domain models + repository layer
⬜ Step 4 — JWT authentication + Spring Security
⬜ Step 5 — Dockerization + Cloud deployment
