package com.devdoc.backend.controller;

import com.devdoc.backend.dto.TrainingDTO;
import com.devdoc.backend.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    // Training 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/trainings")
    public ResponseEntity<TrainingDTO> saveOrUpdateTraining(@PathVariable int resumeId, @RequestBody TrainingDTO trainingDTO) {
        try {
            TrainingDTO updatedTraining = trainingService.saveOrUpdateTraining(resumeId, trainingDTO);
            return ResponseEntity.ok(updatedTraining); // 업데이트된 Training 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 에러 발생 시 500 반환
        }
    }

    // Training 데이터 삭제
    @DeleteMapping("/{resumeId}/trainings/{trainingId}")
    public ResponseEntity<Void> deleteTriaing(@PathVariable int resumeId, @PathVariable int trainingId) {
        try {
            trainingService.deleteTraining(resumeId, trainingId);
            return ResponseEntity.noContent().build(); // educationCompletion 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Training 데이터 수정
    @PutMapping("/{resumeId}/trainings")
    public ResponseEntity<TrainingDTO> updateTraining(@PathVariable int resumeId, @RequestBody TrainingDTO trainingDTO) {
        try {
            TrainingDTO updatedTraining = trainingService.saveOrUpdateTraining(resumeId, trainingDTO);
            return ResponseEntity.ok(updatedTraining); // 수정된 educationCompletion 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
