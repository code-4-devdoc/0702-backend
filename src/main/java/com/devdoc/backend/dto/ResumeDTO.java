package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이력서를 담는 DTO")
public class ResumeDTO {

    @Schema(description = "Resume ID", example = "5")
    private Integer id;

    @Schema(description = "이력서 제목", example = "준비된 백엔드 개발자!")
    private String title;

    @Schema(description = "생성 일자", example = "2024-07-05T10:15:30")
    private LocalDateTime createdAt;

    @Schema(description = "어학 목록")
    private List<LanguageDTO> languages;

    @Schema(description = "수상 이력 목록")
    private List<AwardDTO> awards;

    @Schema(description = "자격증 목록")
    private List<CertificateDTO> certificates;

    @Schema(description = "기술 스택 목록")
    private List<SkillDTO> skills;

    @Schema(description = "경력 목록")
    private List<CareerDTO> careers;

    @Schema(description = "프로젝트 목록")
    private List<ProjectDTO> projects;

    @Schema(description = "대외 활동 목록")
    private List<ActivityDTO> activities;

    @Schema(description = "교육 이수 목록")
    private List<TrainingDTO> trainings;

    @Schema(description = "단일 프로필")
    private AboutMeDTO aboutMe;

    @Schema(description = "학력 목록")
    private List<EducationDTO> educations;

}
