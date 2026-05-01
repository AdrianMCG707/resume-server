package com.adrian.resumeserver.service;

import com.adrian.resumeserver.dto.project.ProjectRequestDto;
import com.adrian.resumeserver.dto.project.ProjectResponseDto;
import com.adrian.resumeserver.model.Project;
import com.adrian.resumeserver.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectResponseDto> getProjectsByUserId(Long userId) {
        return projectRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public ProjectResponseDto createProject(ProjectRequestDto dto) {
        Project project = new Project();
        project.setUserId(dto.getUserId());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setTechStack(dto.getTechStack());
        project.setGithubUrl(dto.getGithubUrl());
        project.setLiveUrl(dto.getLiveUrl());
        return toResponseDto(projectRepository.save(project));
    }

    public Optional<ProjectResponseDto> getProjectById(Long id) {
        return projectRepository.findById(id).map(this::toResponseDto);
    }

    public Optional<ProjectResponseDto> updateProject(Long id, ProjectRequestDto dto) {
        return projectRepository.findById(id).map(project -> {
            project.setName(dto.getName());
            project.setDescription(dto.getDescription());
            project.setTechStack(dto.getTechStack());
            project.setGithubUrl(dto.getGithubUrl());
            project.setLiveUrl(dto.getLiveUrl());
            return toResponseDto(projectRepository.save(project));
        });
    }

    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProjectResponseDto toResponseDto(Project project) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setUserId(project.getUserId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setTechStack(project.getTechStack());
        dto.setGithubUrl(project.getGithubUrl());
        dto.setLiveUrl(project.getLiveUrl());
        dto.setCreatedAt(project.getCreatedAt());
        return dto;
    }
}