package com.devdoc.backend.controller;


import com.devdoc.backend.dto.ResumeDTO;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@Tag(name = "Resume Management", description = "이력서 항목 관리 API")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Operation(summary = "이력서 저장")
    @PostMapping("/{resumeId}/save")
    public ResponseEntity<?> saveResume(@PathVariable int resumeId, @RequestBody ResumeDTO resumeDTO) {
        try {
            resumeService.saveResume(resumeId, resumeDTO);
            return new ResponseEntity<>(HttpStatus.OK); // 이력서 저장 후 200 반환
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 서버 에러 발생 시 500 반환
        }
    }

    @Operation(summary = "이력서 불러오기")
    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeDTO> getResumeByResumeId(@PathVariable int resumeId) {
        try {
            ResumeDTO resumeDTO = resumeService.getResumeByResumeId(resumeId);
            if (resumeDTO != null) {
                return new ResponseEntity<>(resumeDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "이력서 목록 조회")
    @GetMapping
    public ResponseEntity<List<ResumeDTO>> getAllResumesByUser(@AuthenticationPrincipal String userId) {
        try {
            List<ResumeDTO> resumes = resumeService.getAllResumesByUser(userId);
            return new ResponseEntity<>(resumes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "이력서 생성")
    @PostMapping
    public ResponseEntity<Resume> createResume(@RequestBody String title, @AuthenticationPrincipal String userId) {
        try {
            Resume createdResume = resumeService.createResume(title, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResume);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "이력서 삭제")
    @DeleteMapping("/{resumeId}")
    public ResponseEntity<Void> deleteResumeByResumeId(@PathVariable int resumeId) {
        try {
            resumeService.deleteResumeByResumeId(resumeId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "이력서 제목 업데이트", description = "이력서 제목을 업데이트합니다.")
    @PutMapping("/{resumeId}/title")
    public ResponseEntity<ResumeDTO> saveResumeTitleByResumeId(
            @PathVariable int resumeId,
            @RequestBody String newTitle) {
        try {
            ResumeDTO updatedResume = resumeService.saveResumeTitleByResumeId(resumeId, newTitle);
            if (updatedResume != null) {
                return ResponseEntity.ok(updatedResume);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
