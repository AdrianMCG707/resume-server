package com.adrian.resumeserver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructors
    public Note() {}

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}

/*

@Entity — tells JPA this class maps to a database table
@Table(name = "notes") — maps it specifically to the notes table you just created in V1__init.sql
@Id + @GeneratedValue — maps to your BIGSERIAL PRIMARY KEY column, Postgres auto-generates the number
@PrePersist — the onCreate() method runs automatically right before a new row is inserted, setting createdAt so you never have to set it manually
The no-arg constructor public Note() {} is required — JPA uses reflection to create objects and needs it

 */