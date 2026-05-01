package com.adrian.resumeserver.service;

import com.adrian.resumeserver.dto.skill.SkillRequestDto;
import com.adrian.resumeserver.dto.skill.SkillResponseDto;
import com.adrian.resumeserver.model.Skill;
import com.adrian.resumeserver.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<SkillResponseDto> getSkillsByUserId(Long userId) {
        return skillRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public SkillResponseDto createSkill(SkillRequestDto dto) {
        Skill skill = new Skill();
        skill.setUserId(dto.getUserId());
        skill.setName(dto.getName());
        skill.setCategory(dto.getCategory());
        skill.setProficiency(dto.getProficiency());
        return toResponseDto(skillRepository.save(skill));
    }

    public Optional<SkillResponseDto> getSkillById(Long id) {
        return skillRepository.findById(id).map(this::toResponseDto);
    }

    public Optional<SkillResponseDto> updateSkill(Long id, SkillRequestDto dto) {
        return skillRepository.findById(id).map(skill -> {
            skill.setName(dto.getName());
            skill.setCategory(dto.getCategory());
            skill.setProficiency(dto.getProficiency());
            return toResponseDto(skillRepository.save(skill));
        });
    }

    public boolean deleteSkill(Long id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SkillResponseDto toResponseDto(Skill skill) {
        SkillResponseDto dto = new SkillResponseDto();
        dto.setId(skill.getId());
        dto.setUserId(skill.getUserId());
        dto.setName(skill.getName());
        dto.setCategory(skill.getCategory());
        dto.setProficiency(skill.getProficiency());
        dto.setCreatedAt(skill.getCreatedAt());
        return dto;
    }
}