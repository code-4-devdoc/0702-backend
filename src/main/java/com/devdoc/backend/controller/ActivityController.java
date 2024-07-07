package com.devdoc.backend.controller;

import com.devdoc.backend.dto.ActivityDTO;
import com.devdoc.backend.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@Tag(name = "Activity Management", description = "대외 활동(Activity) 항목 관리 API")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Operation(summary = "대외 활동 저장 또는 수정")
    @PostMapping("/{resumeId}/activities")
    public ResponseEntity<ActivityDTO> saveOrUpdateActivity(@PathVariable int resumeId, @RequestBody ActivityDTO activityDTO) {
        try {
            ActivityDTO updatedActivity = activityService.saveOrUpdateActivity(resumeId, activityDTO);
            return ResponseEntity.ok(updatedActivity); // 업데이트된 Activity 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 에러 발생 시 500 반환
        }
    }

    @Operation(summary = "대외 활동 삭제")
    @DeleteMapping("/{resumeId}/activities/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable int resumeId, @PathVariable int activityId) {
        try {
            activityService.deleteActivity(resumeId, activityId);
            return ResponseEntity.noContent().build(); // Activity 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "대외 활동 수정")
    @PutMapping("/{resumeId}/activities")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable int resumeId, @RequestBody ActivityDTO activityDTO) {
        try {
            ActivityDTO updatedActivity = activityService.saveOrUpdateActivity(resumeId, activityDTO);
            return ResponseEntity.ok(updatedActivity); // 수정된 Activity 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
