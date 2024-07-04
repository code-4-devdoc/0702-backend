package com.devdoc.backend.controller;

import com.devdoc.backend.dto.AboutMeDTO;
import com.devdoc.backend.service.AboutMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
public class AboutMeController {

    @Autowired
    private AboutMeService aboutMeService;

    // AboutMe 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/aboutMes")
    public ResponseEntity<AboutMeDTO> saveOrUpdateAboutMe(@PathVariable("resumeId") int resumeId, @RequestBody AboutMeDTO aboutMeDTO) {
        try {
            AboutMeDTO updatedAboutMe = aboutMeService.saveOrUpdateAboutMe(resumeId, aboutMeDTO);
            return ResponseEntity.ok(updatedAboutMe); // 업데이트된 AboutMe 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 에러 발생 시 500 반환
        }
    }

    // AboutMe 데이터 삭제
    @DeleteMapping("/{resumeId}/aboutMes/{aboutMeId}")
    public ResponseEntity<Void> deleteAboutMe(@PathVariable("resumeId") int resumeId, @PathVariable("aboutMeId") int aboutMeId) {
        try {
            aboutMeService.deleteAboutMe(resumeId, aboutMeId);
            return ResponseEntity.noContent().build(); // AboutMe 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // AboutMe 데이터 수정
    @PutMapping("/{resumeId}/aboutMes")
    public ResponseEntity<AboutMeDTO> updateAboutMe(@PathVariable("resumeId") int resumeId, @RequestBody AboutMeDTO aboutMeDTO) {
        try {
            AboutMeDTO updatedAboutMe = aboutMeService.saveOrUpdateAboutMe(resumeId, aboutMeDTO);
            return ResponseEntity.ok(updatedAboutMe); // 수정된 AboutMe 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // AboutMe 데이터 조회
    @GetMapping("/{resumeId}/aboutMes")
    public ResponseEntity<AboutMeDTO> getAboutMe(@PathVariable("resumeId") int resumeId) {
        try {
            AboutMeDTO aboutMeDTO = aboutMeService.getAboutMeByResumeId(resumeId);
            if (aboutMeDTO != null) {
                return ResponseEntity.ok(aboutMeDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
