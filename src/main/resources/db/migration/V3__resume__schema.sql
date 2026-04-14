-- V3__resume_schema.sql
-- Creates all resume entities: users, projects, skills, experience, education.
-- All tables reference users.id via foreign key — one user owns everything.
-- Indexes added on all user_id columns for fast lookups.

CREATE TABLE users (
                       id          BIGSERIAL PRIMARY KEY,
                       email       VARCHAR(255) NOT NULL UNIQUE,
                       full_name   VARCHAR(255) NOT NULL,
                       title       VARCHAR(255),
                       location    VARCHAR(255),
                       summary     TEXT,
                       created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE projects (
                          id          BIGSERIAL PRIMARY KEY,
                          user_id     BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          name        VARCHAR(255) NOT NULL,
                          description TEXT,
                          tech_stack  VARCHAR(255),
                          github_url  VARCHAR(255),
                          live_url    VARCHAR(255),
                          created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE skills (
                        id          BIGSERIAL PRIMARY KEY,
                        user_id     BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                        name        VARCHAR(255) NOT NULL,
                        category    VARCHAR(255),
                        proficiency VARCHAR(50),
                        created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE experience (
                            id          BIGSERIAL PRIMARY KEY,
                            user_id     BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                            company     VARCHAR(255) NOT NULL,
                            role        VARCHAR(255) NOT NULL,
                            start_date  DATE NOT NULL,
                            end_date    DATE,
                            description TEXT,
                            created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE education (
                           id              BIGSERIAL PRIMARY KEY,
                           user_id         BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                           institution     VARCHAR(255) NOT NULL,
                           degree          VARCHAR(255) NOT NULL,
                           field_of_study  VARCHAR(255),
                           graduation_date DATE,
                           created_at      TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Indexes on all foreign key columns for fast user-based lookups
CREATE INDEX idx_projects_user_id   ON projects(user_id);
CREATE INDEX idx_skills_user_id     ON skills(user_id);
CREATE INDEX idx_experience_user_id ON experience(user_id);
CREATE INDEX idx_education_user_id  ON education(user_id);