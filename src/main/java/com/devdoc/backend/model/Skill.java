package com.devdoc.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "기술 스택 정보를 담는 모델")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Skill ID", example = "1")
    private Integer id;

    @Schema(description = "기술 스택", example = "Spring Boot")
    private String techStack;

    @Schema(description = "기술에 대한 상세 설명", example = "다양한 프로젝트에 활용함.")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
