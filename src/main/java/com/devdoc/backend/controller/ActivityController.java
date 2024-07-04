package com.devdoc.backend.controller;

import com.devdoc.backend.dto.ActivityDTO;
import com.devdoc.backend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    // Activity 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/activities")
    public ResponseEntity<ActivityDTO> saveOrUpdateActivity(@PathVariable("resumeId") int resumeId, @RequestBody ActivityDTO activityDTO) {
        try {
            ActivityDTO updatedActivity = activityService.saveOrUpdateActivity(resumeId, activityDTO);
            return ResponseEntity.ok(updatedActivity); // 업데이트된 activity 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 에러 발생 시 500 반환
        }
    }

    // Activity 데이터 삭제
    @DeleteMapping("/{resumeId}/activities/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable("resumeId") int resumeId, @PathVariable("activityId") int activityId) {
        try {
            activityService.deleteActivity(resumeId, activityId);
            return ResponseEntity.noContent().build(); // certification 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Activity 데이터 수정
    @PutMapping("/{resumeId}/activities")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable("resumeId") int resumeId, @RequestBody ActivityDTO activityDTO) {
        try {
            ActivityDTO updatedActivity = activityService.saveOrUpdateActivity(resumeId, activityDTO);
            return ResponseEntity.ok(updatedActivity); // 수정된 certification 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 특정 Resume의 모든 Activity 조회
    @GetMapping("/{resumeId}/activities")
    public ResponseEntity<List<ActivityDTO>> getActivitiesByResumeId(@PathVariable("resumeId") int resumeId) {
        try {
            List<ActivityDTO> activities = activityService.getActivitiesByResumeId(resumeId);
            if (activities != null) {
                return new ResponseEntity<>(activities, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
