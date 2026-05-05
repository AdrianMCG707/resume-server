package com.adrian.resumeserver.service;

import com.adrian.resumeserver.dto.project.ProjectRequestDto;
import com.adrian.resumeserver.dto.project.ProjectResponseDto;
import com.adrian.resumeserver.model.Project;
import com.adrian.resumeserver.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.eq;


@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private AuditLogService auditLogService;

    @InjectMocks
    private ProjectService projectService;

    private Project sampleProject() {
        Project p = new Project();
        p.setUserId(1L);
        p.setName("Resume Server");
        p.setDescription("A Spring Boot API");
        p.setTechStack("Java");
        p.setGithubUrl("github.com/test");
        p.setLiveUrl("test.com");
        return p;
    }

    private ProjectRequestDto sampleRequest() {
        ProjectRequestDto dto = new ProjectRequestDto();
        dto.setUserId(1L);
        dto.setName("Resume Server");
        dto.setDescription("A Spring Boot API");
        dto.setTechStack("Java");
        dto.setGithubUrl("github.com/test");
        dto.setLiveUrl("test.com");
        return dto;
    }

    @Test
    void createProject_shouldReturnResponseDto() {
        when(projectRepository.save(any(Project.class))).thenReturn(sampleProject());

        ProjectResponseDto result = projectService.createProject(sampleRequest());

        assertThat(result.getName()).isEqualTo("Resume Server");
        verify(auditLogService).log(eq("CREATE"), eq("Project"), any());
    }

    @Test
    void getProjectById_shouldReturnDto_whenFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(sampleProject()));

        Optional<ProjectResponseDto> result = projectService.getProjectById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Resume Server");
    }

    @Test
    void getProjectById_shouldReturnEmpty_whenNotFound() {
        when(projectRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<ProjectResponseDto> result = projectService.getProjectById(99L);

        assertThat(result).isEmpty();
    }

    @Test
    void getProjectsByUserId_shouldReturnList() {
        when(projectRepository.findByUserId(1L)).thenReturn(List.of(sampleProject()));

        List<ProjectResponseDto> result = projectService.getProjectsByUserId(1L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Resume Server");
    }

    @Test
    void deleteProject_shouldReturnTrue_whenExists() {
        when(projectRepository.existsById(1L)).thenReturn(true);

        boolean result = projectService.deleteProject(1L);

        assertThat(result).isTrue();
        verify(projectRepository).deleteById(1L);
        verify(auditLogService).log("DELETE", "Project", 1L);
    }

    @Test
    void deleteProject_shouldReturnFalse_whenNotExists() {
        when(projectRepository.existsById(99L)).thenReturn(false);

        boolean result = projectService.deleteProject(99L);

        assertThat(result).isFalse();
        verify(projectRepository, never()).deleteById(any());
    }

}