package com.devdoc.backend.controller;

import com.devdoc.backend.dto.EducationDTO;
import com.devdoc.backend.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class EducationController {

    @Autowired
    private EducationService educationService;

    // Education 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/educations")
    public ResponseEntity<EducationDTO> saveOrUpdateEducation(@PathVariable("resumeId") int resumeId, @RequestBody EducationDTO educationDTO) {
        try {
            EducationDTO updatedEducation = educationService.saveOrUpdateEducation(resumeId, educationDTO);
            return ResponseEntity.ok(updatedEducation); // 업데이트된 education 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 에러 발생 시 500 반환
        }
    }

    // Education 데이터 삭제
    @DeleteMapping("/{resumeId}/educations/{educationId}")
    public ResponseEntity<Void> deleteEducation(@PathVariable("resumeId") int resumeId, @PathVariable("educationId") int educationId) {
        try {
            educationService.deleteEducation(resumeId, educationId);
            return ResponseEntity.noContent().build(); // education 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Education 데이터 수정
    @PutMapping("/{resumeId}/educations")
    public ResponseEntity<EducationDTO> updateEducation(@PathVariable("resumeId") int resumeId, @RequestBody EducationDTO educationDTO) {
        try {
            EducationDTO updatedEducation = educationService.saveOrUpdateEducation(resumeId, educationDTO);
            return ResponseEntity.ok(updatedEducation); // 수정된 education 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 특정 Resume의 모든 Education 조회
    @GetMapping("/{resumeId}/educations")
    public ResponseEntity<List<EducationDTO>> getEducationsByResumeId(@PathVariable("resumeId") int resumeId) {
        try {
            List<EducationDTO> educations = educationService.getEducationsByResumeId(resumeId);
            if (educations != null) {
                return new ResponseEntity<>(educations, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
