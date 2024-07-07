package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "기술 스택 항목을 담는 DTO")
public class SkillDTO {

    @Schema(description = "Skill ID", example = "1")
    private Integer id;

    @Schema(description = "기술 스택", example = "Spring Boot")
    private String techStack;

    @Schema(description = "기술에 대한 상세 설명", example = "다양한 프로젝트에 활용함.")
    private String description;
}
