package com.adrian.resumeserver.controller;

import com.adrian.resumeserver.dto.skill.SkillRequestDto;
import com.adrian.resumeserver.dto.skill.SkillResponseDto;
import com.adrian.resumeserver.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<SkillResponseDto> getSkillsByUserId(@RequestParam Long userId) {
        return skillService.getSkillsByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillResponseDto createSkill(@Valid @RequestBody SkillRequestDto dto) {
        return skillService.createSkill(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponseDto> getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillResponseDto> updateSkill(
            @PathVariable Long id,
            @Valid @RequestBody SkillRequestDto dto) {
        return skillService.updateSkill(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        return skillService.deleteSkill(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}