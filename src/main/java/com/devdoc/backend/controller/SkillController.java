package com.devdoc.backend.controller;

import com.devdoc.backend.dto.SkillDTO;
import com.devdoc.backend.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@Tag(name = "Skill Management", description = "기술 스택(Skill) 항목 관리 API")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Operation(summary = "기술 스택 저장 또는 수정")
    @PostMapping("/{resumeId}/skills")
    public ResponseEntity<SkillDTO> saveOrUpdateSkill(@PathVariable int resumeId, @RequestBody SkillDTO skillDTO) {
        try {
            SkillDTO updatedSkill = skillService.saveOrUpdateSkill(resumeId, skillDTO);
            return ResponseEntity.ok(updatedSkill); // 수정된 Skill 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "기술 스택 삭제")
    @DeleteMapping("/{resumeId}/skills/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable int resumeId, @PathVariable int skillId) {
        try {
            skillService.deleteSkill(resumeId, skillId);
            return ResponseEntity.noContent().build(); // Skill 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "기술 스택 수정")
    @PutMapping("/{resumeId}/skills")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable int resumeId, @RequestBody SkillDTO skillDTO) {
        try {
            SkillDTO updatedSkill = skillService.saveOrUpdateSkill(resumeId, skillDTO);
            return ResponseEntity.ok(updatedSkill); // 업데이트된 Skill 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
