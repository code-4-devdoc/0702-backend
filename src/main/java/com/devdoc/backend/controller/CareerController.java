package com.devdoc.backend.controller;

import com.devdoc.backend.dto.CareerDTO;
import com.devdoc.backend.service.CareerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@Tag(name = "Career Management", description = "경력(Career) 항목 관리 API")
public class CareerController {

    @Autowired
    private CareerService careerService;

    @Operation(summary = "경력 저장 또는 수정")
    @PostMapping("/{resumeId}/careers")
    public ResponseEntity<CareerDTO> saveOrUpdateCareer(@PathVariable int resumeId, @RequestBody CareerDTO careerDTO) {
        try {
            CareerDTO updatedCareer = careerService.saveOrUpdateCareer(resumeId, careerDTO);
            return ResponseEntity.ok(updatedCareer); // 수정된 Career 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "경력 삭제", description = "경력 데이터를 삭제합니다.")
    @DeleteMapping("/{resumeId}/careers/{careerId}")
    public ResponseEntity<Void> deleteCareer(@PathVariable int resumeId, @PathVariable int careerId) {
        try {
            careerService.deleteCareer(resumeId, careerId);
            return ResponseEntity.noContent().build(); // Career 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "경력 수정", description = "경력 데이터를 수정합니다.")
    @PutMapping("/{resumeId}/careers")
    public ResponseEntity<CareerDTO> updateCareer(@PathVariable int resumeId, @RequestBody CareerDTO careerDTO) {
        try {
            CareerDTO updatedCareer = careerService.saveOrUpdateCareer(resumeId, careerDTO);
            return ResponseEntity.ok(updatedCareer); // 업데이트된 Career 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
