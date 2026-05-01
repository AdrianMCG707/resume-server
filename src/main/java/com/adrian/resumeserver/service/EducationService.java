package com.adrian.resumeserver.service;

import com.adrian.resumeserver.dto.education.EducationRequestDto;
import com.adrian.resumeserver.dto.education.EducationResponseDto;
import com.adrian.resumeserver.model.Education;
import com.adrian.resumeserver.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<EducationResponseDto> getEducationByUserId(Long userId) {
        return educationRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public EducationResponseDto createEducation(EducationRequestDto dto) {
        Education education = new Education();
        education.setUserId(dto.getUserId());
        education.setInstitution(dto.getInstitution());
        education.setDegree(dto.getDegree());
        education.setFieldOfStudy(dto.getFieldOfStudy());
        education.setGraduationDate(dto.getGraduationDate());
        return toResponseDto(educationRepository.save(education));
    }

    public Optional<EducationResponseDto> getEducationById(Long id) {
        return educationRepository.findById(id).map(this::toResponseDto);
    }

    public Optional<EducationResponseDto> updateEducation(Long id, EducationRequestDto dto) {
        return educationRepository.findById(id).map(education -> {
            education.setInstitution(dto.getInstitution());
            education.setDegree(dto.getDegree());
            education.setFieldOfStudy(dto.getFieldOfStudy());
            education.setGraduationDate(dto.getGraduationDate());
            return toResponseDto(educationRepository.save(education));
        });
    }

    public boolean deleteEducation(Long id) {
        if (educationRepository.existsById(id)) {
            educationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private EducationResponseDto toResponseDto(Education education) {
        EducationResponseDto dto = new EducationResponseDto();
        dto.setId(education.getId());
        dto.setUserId(education.getUserId());
        dto.setInstitution(education.getInstitution());
        dto.setDegree(education.getDegree());
        dto.setFieldOfStudy(education.getFieldOfStudy());
        dto.setGraduationDate(education.getGraduationDate());
        dto.setCreatedAt(education.getCreatedAt());
        return dto;
    }
}