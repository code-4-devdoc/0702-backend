package com.devdoc.backend.controller;

import com.devdoc.backend.dto.SkillDTO;
import com.devdoc.backend.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class SkillController {

    @Autowired
    private SkillService skillService;

    // Skill 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/skills")
    public ResponseEntity<SkillDTO> saveOrUpdateSkill(@PathVariable("resumeId") int resumeId, @RequestBody SkillDTO skillDTO) {
        try {
            SkillDTO updatedSkill = skillService.saveOrUpdateSkill(resumeId, skillDTO);
            return ResponseEntity.ok(updatedSkill); // 수정된 Skill 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Skill 데이터 삭제
    @DeleteMapping("/{resumeId}/skills/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable("resumeId") int resumeId, @PathVariable("skillId") int skillId) {
        try {
            skillService.deleteSkill(resumeId, skillId);
            return ResponseEntity.noContent().build(); //Skill 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Skill 데이터 수정
    @PutMapping("/{resumeId}/skills")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable("resumeId") int resumeId, @RequestBody SkillDTO skillDTO) {
        try {
            SkillDTO updatedSkill = skillService.saveOrUpdateSkill(resumeId, skillDTO);
            return ResponseEntity.ok(updatedSkill); // 업데이트된 Skill 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 특정 Resume의 모든 Skill 조회
    @GetMapping("/{resumeId}/skills")
    public ResponseEntity<List<SkillDTO>> getSkillsByResumeId(@PathVariable("resumeId") int resumeId) {
        try {
            List<SkillDTO> skills = skillService.getSkillsByResumeId(resumeId);
            if (skills != null) {
                return new ResponseEntity<>(skills, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
