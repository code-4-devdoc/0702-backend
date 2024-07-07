package com.devdoc.backend.controller;

import com.devdoc.backend.dto.AboutMeDTO;
import com.devdoc.backend.service.AboutMeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@Tag(name = "AboutMe Management", description = "프로필(AboutMe) 항목 관리 API")
public class AboutMeController {

    @Autowired
    private AboutMeService aboutMeService;

    @Operation(summary = "프로필 저장 또는 수정")
    @PostMapping("/{resumeId}/aboutMes")
    public ResponseEntity<AboutMeDTO> saveOrUpdateAboutMe(@PathVariable int resumeId, @RequestBody AboutMeDTO aboutMeDTO) {
        try {
            AboutMeDTO updatedAboutMe = aboutMeService.saveOrUpdateAboutMe(resumeId, aboutMeDTO);
            return ResponseEntity.ok(updatedAboutMe); // 업데이트된 AboutMe 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 에러 발생 시 500 반환
        }
    }

    @Operation(summary = "프로필 삭제")
    @DeleteMapping("/{resumeId}/aboutMes/{aboutMeId}")
    public ResponseEntity<Void> deleteAboutMe(@PathVariable int resumeId, @PathVariable int aboutMeId) {
        try {
            aboutMeService.deleteAboutMe(resumeId, aboutMeId);
            return ResponseEntity.noContent().build(); // AboutMe 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "프로필 수정")
    @PutMapping("/{resumeId}/aboutMes")
    public ResponseEntity<AboutMeDTO> updateAboutMe(@PathVariable int resumeId, @RequestBody AboutMeDTO aboutMeDTO) {
        try {
            AboutMeDTO updatedAboutMe = aboutMeService.saveOrUpdateAboutMe(resumeId, aboutMeDTO);
            return ResponseEntity.ok(updatedAboutMe); // 수정된 AboutMe 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
