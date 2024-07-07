package com.devdoc.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "프로젝트 항목을 담는 DTO")
public class ProjectDTO {

    @Schema(description = "Project ID", example = "1")
    private Integer id;

    @Schema(description = "프로젝트 제목", example = "Devdoc")
    private String title;

    @Schema(description = "시작 날짜", example = "2023-01-01")
    private String startDate;

    @Schema(description = "종료 날짜", example = "2023-12-31")
    private String endDate;

    @Schema(description = "현재 진행 중 여부", example = "true")
    private Boolean isCurrent;

    @Schema(description = "프로젝트 소개", example = "개발자를 위한 이력서 작성 웹서비스입니다.")
    private String intro;

    @Schema(description = "기술 스택", example = "Java, Spring Boot, React")
    private String techStack;

    @Schema(description = "프로젝트 설명", example = "프로젝트에 대한 자세한 설명...")
    private String description;
}

