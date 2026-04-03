-- V2__seed_data.sql
-- Seed data migration — inserts sample notes for demo purposes.
-- Flyway runs this exactly once, after V1__init.sql.
-- This gives the app real data without manually calling POST /notes every time.

INSERT INTO notes (title, content, created_at) VALUES
('Welcome to Resume Server', 'This is a demo note created by the seed migration.', NOW()),
('Spring Boot Basics', 'Spring Boot auto-configures your app based on dependencies in pom.xml.', NOW()),
('Flyway Migrations', 'Flyway tracks schema changes in flyway_schema_history. V1 creates tables, V2 seeds data.', NOW()),
('Docker Setup', 'PostgreSQL runs in a Docker container exposed on localhost:5432.', NOW()),
('REST API Design', 'GET retrieves data, POST creates data. Always return appropriate HTTP status codes.', NOW());