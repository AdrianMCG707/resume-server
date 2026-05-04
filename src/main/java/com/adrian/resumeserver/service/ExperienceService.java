package com.adrian.resumeserver.service;

import com.adrian.resumeserver.dto.experience.ExperienceRequestDto;
import com.adrian.resumeserver.dto.experience.ExperienceResponseDto;
import com.adrian.resumeserver.model.Experience;
import com.adrian.resumeserver.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final AuditLogService auditLogService;  // ← NEW

    public ExperienceService(ExperienceRepository experienceRepository,
                             AuditLogService auditLogService) {  // ← NEW
        this.experienceRepository = experienceRepository;
        this.auditLogService = auditLogService;  // ← NEW
    }

    public List<ExperienceResponseDto> getExperienceByUserId(Long userId) {
        return experienceRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public ExperienceResponseDto createExperience(ExperienceRequestDto dto) {
        Experience experience = new Experience();
        experience.setUserId(dto.getUserId());
        experience.setCompany(dto.getCompany());
        experience.setRole(dto.getRole());
        experience.setStartDate(dto.getStartDate());
        experience.setEndDate(dto.getEndDate());
        experience.setDescription(dto.getDescription());
        ExperienceResponseDto result = toResponseDto(experienceRepository.save(experience));  // ← CHANGED
        auditLogService.log("CREATE", "Experience", result.getId());  // ← NEW
        return result;  // ← CHANGED
    }

    public Optional<ExperienceResponseDto> getExperienceById(Long id) {
        return experienceRepository.findById(id).map(this::toResponseDto);
    }

    public Optional<ExperienceResponseDto> updateExperience(Long id, ExperienceRequestDto dto) {
        return experienceRepository.findById(id).map(experience -> {
            experience.setCompany(dto.getCompany());
            experience.setRole(dto.getRole());
            experience.setStartDate(dto.getStartDate());
            experience.setEndDate(dto.getEndDate());
            experience.setDescription(dto.getDescription());
            ExperienceResponseDto result = toResponseDto(experienceRepository.save(experience));  // ← CHANGED
            auditLogService.log("UPDATE", "Experience", result.getId());  // ← NEW
            return result;  // ← CHANGED
        });
    }

    public boolean deleteExperience(Long id) {
        if (experienceRepository.existsById(id)) {
            experienceRepository.deleteById(id);
            auditLogService.log("DELETE", "Experience", id);  // ← NEW
            return true;
        }
        return false;
    }

    private ExperienceResponseDto toResponseDto(Experience experience) {
        ExperienceResponseDto dto = new ExperienceResponseDto();
        dto.setId(experience.getId());
        dto.setUserId(experience.getUserId());
        dto.setCompany(experience.getCompany());
        dto.setRole(experience.getRole());
        dto.setStartDate(experience.getStartDate());
        dto.setEndDate(experience.getEndDate());
        dto.setDescription(experience.getDescription());
        dto.setCreatedAt(experience.getCreatedAt());
        return dto;
    }
}