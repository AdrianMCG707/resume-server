package com.adrian.resumeserver.dto.user;

import java.time.LocalDateTime;

public class UserResponseDto {

    private Long id;
    private String email;
    private String fullName;
    private String title;
    private String location;
    private String summary;
    private LocalDateTime createdAt;

    public UserResponseDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

/*
Key things to notice:

@NotBlank — field cannot be null or empty string
@Email — validates the email format automatically
@Size — enforces max length before it even hits the database
UserResponseDto includes id and createdAt — fields the server generates, not the client
UserRequestDto has no id or createdAt — the client never sends those




 */