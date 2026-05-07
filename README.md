# Resume Server

A production-style REST API backend that stores and serves resume data through
HTTP endpoints. Built to simulate how real backend systems work — data lives in
a database and is exposed through REST APIs rather than a static file.

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot 3.4.1 | Backend framework + embedded Tomcat |
| Spring Data JPA / Hibernate | Database ORM layer |
| PostgreSQL 16 | Production database |
| Flyway | Database migration versioning |
| Docker + Docker Compose | Local database container |
| SpringDoc OpenAPI (Swagger) | Auto-generated API documentation |
| Spring Boot Actuator | Health check endpoint |
| Maven | Build tool and dependency management |

---

## Architecture
```
HTTP Request
    ↓
NoteController  (REST layer — handles HTTP)
    ↓
NoteService     (Business logic layer)
    ↓
NoteRepository  (Data access layer — Spring Data JPA)
    ↓
PostgreSQL      (Running in Docker)
```

---

## Running Locally

### Prerequisites
- Java 21
- Docker Desktop
- Maven (or use the included `./mvnw` wrapper)

### Step 1 — Start the database
```bash
docker compose up -d
```

### Step 2 — Start the server
```bash
./mvnw spring-boot:run
```

### Step 3 — Verify it's running
Visit: `http://localhost:8080/actuator/health`

Expected response:
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP"
    }
  }
}
```

---

## API Documentation

Swagger UI is available at:
```
http://localhost:8080/swagger-ui/index.html
```

---

## Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | /ping | Server health check | No |
| GET | /actuator/health | Database health status | No |
| POST | /auth/register | Create a new account | No |
| POST | /auth/login | Login and receive JWT token | No |
| GET | /resume/users | List all users | No |
| POST | /resume/users | Create a user | Yes |
| GET | /resume/projects | List all projects | No |
| POST | /resume/projects | Create a project | Yes |
| PUT | /resume/projects/{id} | Update a project | Yes |
| DELETE | /resume/projects/{id} | Delete a project | Yes |
| GET | /resume/skills | List all skills | No |
| GET | /resume/experience | List all experience | No |
| GET | /resume/education | List all education | No |
| GET | /admin/audit-logs | View audit log | Yes (Admin) |
---

## Database Migrations

Managed by Flyway — migrations run automatically on startup:

| Version | File | Description |
|---|---|---|
| V1 | V1__init.sql | Creates the notes table |
| V2 | V2__seed_data.sql | Inserts sample demo data |

---

## Environment Variables

Copy `.env.example` to `.env` and fill in your values:
```bash
cp .env.example .env
```

---

## Project Status

- ✅ Step 1 — Server bootstrapped, /ping endpoint working
- ✅ Step 2 — PostgreSQL, Flyway, JPA, full CRUD for notes, tests passing
- ✅ Step 3 — Health endpoint, seed data, Docker setup documented
- ✅ Step 4 — Full resume data model (ERD + all entities)
- ✅ Step 5 — Complete CRUD APIs for all resume entities
- ✅ Step 6 — JWT Authentication + Spring Security
- ✅ Step 7 — Standout feature (Audit Logging)
- ✅ Step 8 — Testing + quality pass (15 tests passing)
- 🔄 Step 9 — Productionize with Docker (in progress)
- ⬜ Step 10 — Deploy to cloud (Render/Railway)
