package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "경력 항목을 담는 DTO")
public class CareerDTO {

    @Schema(description = "Career ID", example = "1")
    private Integer id;

    @Schema(description = "회사명", example = "구글")
    private String company;

    @Schema(description = "부서명", example = "SW 개발 부서")
    private String department;

    @Schema(description = "입사일", example = "2021-01-01")
    private String startDate;

    @Schema(description = "퇴사일", example = "2023-01-01")
    private String endDate;

    @Schema(description = "재직 중 여부", example = "true")
    private Boolean isCurrent;

    @Schema(description = "기술 스택", example = "Java, Spring Boot")
    private String techStack;

    @Schema(description = "경력 설명", example = "경력에 대한 자세한 설명...")
    private String description;
}
