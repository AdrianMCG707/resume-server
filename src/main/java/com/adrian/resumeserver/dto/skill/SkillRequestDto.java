package com.adrian.resumeserver.dto.skill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SkillRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Skill name is required")
    @Size(max = 255, message = "Name must be under 255 characters")
    private String name;

    private String category;
    private String proficiency;

    public SkillRequestDto() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getProficiency() { return proficiency; }
    public void setProficiency(String proficiency) { this.proficiency = proficiency; }
}