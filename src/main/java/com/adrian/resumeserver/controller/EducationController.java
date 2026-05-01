package com.adrian.resumeserver.controller;

import com.adrian.resumeserver.dto.education.EducationRequestDto;
import com.adrian.resumeserver.dto.education.EducationResponseDto;
import com.adrian.resumeserver.service.EducationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<EducationResponseDto> getEducationByUserId(@RequestParam Long userId) {
        return educationService.getEducationByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationResponseDto createEducation(@Valid @RequestBody EducationRequestDto dto) {
        return educationService.createEducation(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponseDto> getEducationById(@PathVariable Long id) {
        return educationService.getEducationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationResponseDto> updateEducation(
            @PathVariable Long id,
            @Valid @RequestBody EducationRequestDto dto) {
        return educationService.updateEducation(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        return educationService.deleteEducation(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}