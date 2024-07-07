package com.devdoc.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "경력 정보를 담는 모델")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "경력 ID", example = "1")
    private Integer id;

    @Schema(description = "회사 이름", example = "Samsung Electronics")
    private String company;

    @Schema(description = "부서 이름", example = "Software Development")
    private String department;

    @Schema(description = "시작 날짜", example = "2021-01-01")
    private String startDate;

    @Schema(description = "종료 날짜", example = "2023-01-01")
    private String endDate;

    @Schema(description = "현재 진행 중 여부", example = "true")
    private Boolean isCurrent;

    @Schema(description = "기술 스택", example = "Java, Spring Boot")
    private String techStack;

    @Schema(description = "경력 설명", example = "Worked on various software development projects.")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
