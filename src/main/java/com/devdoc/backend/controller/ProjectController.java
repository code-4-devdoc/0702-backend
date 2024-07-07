package com.devdoc.backend.controller;

import com.devdoc.backend.dto.ProjectDTO;
import com.devdoc.backend.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@Tag(name = "Project Management", description = "프로젝트(Project) 항목 관리 API")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "프로젝트 저장 또는 수정")
    @PostMapping("/{resumeId}/projects")
    public ResponseEntity<ProjectDTO> saveOrUpdateProject(@PathVariable int resumeId, @RequestBody ProjectDTO projectDTO) {
        try {
            ProjectDTO updatedProject = projectService.saveOrUpdateProject(resumeId, projectDTO);
            return ResponseEntity.ok(updatedProject); // 수정된 Project 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "프로젝트 삭제")
    @DeleteMapping("/{resumeId}/projects/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable int resumeId, @PathVariable int projectId) {
        try {
            projectService.deleteProject(resumeId, projectId);
            return ResponseEntity.noContent().build(); // Project 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "프로젝트 수정")
    @PutMapping("/{resumeId}/projects")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable int resumeId, @RequestBody ProjectDTO projectDTO) {
        try {
            ProjectDTO updatedProject = projectService.saveOrUpdateProject(resumeId, projectDTO);
            return ResponseEntity.ok(updatedProject); // 업데이트된 Project 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
