package com.devdoc.backend.controller;

import com.devdoc.backend.dto.CareerDTO;
import com.devdoc.backend.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class CareerController {

    @Autowired
    private CareerService careerService;

    // Career 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/careers")
    public ResponseEntity<CareerDTO> saveOrUpdateCareer(@PathVariable("resumeId") int resumeId, @RequestBody CareerDTO careerDTO) {
        try {
            CareerDTO updatedCareer = careerService.saveOrUpdateCareer(resumeId, careerDTO);
            return ResponseEntity.ok(updatedCareer); // 수정된 Career 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Career 데이터 삭제
    @DeleteMapping("/{resumeId}/careers/{careerId}")
    public ResponseEntity<Void> deleteCareer(@PathVariable("resumeId") int resumeId, @PathVariable("careerId") int careerId) {
        try {
            careerService.deleteCareer(resumeId, careerId);
            return ResponseEntity.noContent().build(); //Career 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Career 데이터 수정
    @PutMapping("/{resumeId}/careers")
    public ResponseEntity<CareerDTO> updateCareer(@PathVariable("resumeId") int resumeId, @RequestBody CareerDTO careerDTO) {
        try {
            CareerDTO updatedCareer = careerService.saveOrUpdateCareer(resumeId, careerDTO);
            return ResponseEntity.ok(updatedCareer); // 업데이트된 Career 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 특정 Resume의 모든 Career 조회
    @GetMapping("/{resumeId}/careers")
    public ResponseEntity<List<CareerDTO>> getCareersByResumeId(@PathVariable("resumeId") int resumeId) {
        try {
            List<CareerDTO> careers = careerService.getCareersByResumeId(resumeId);
            if (careers != null) {
                return new ResponseEntity<>(careers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
