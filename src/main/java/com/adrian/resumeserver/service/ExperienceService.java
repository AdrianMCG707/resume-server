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

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
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
        return toResponseDto(experienceRepository.save(experience));
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
            return toResponseDto(experienceRepository.save(experience));
        });
    }

    public boolean deleteExperience(Long id) {
        if (experienceRepository.existsById(id)) {
            experienceRepository.deleteById(id);
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