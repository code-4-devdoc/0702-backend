package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "교육 이수 항목을 담는 DTO")
public class TrainingDTO {

    @Schema(description = "Training ID", example = "1")
    private Integer id;

    @Schema(description = "교육명", example = "[카카오x구름] 자바 스프링 & 리액트 풀스택 개발자 성장 과정")
    private String courseName;

    @Schema(description = "교육 기관명", example = "(주)구름")
    private String institution;

    @Schema(description = "시작 날짜", example = "2023-01-01")
    private String startDate;

    @Schema(description = "종료 날짜", example = "2023-06-01")
    private String endDate;

    @Schema(description = "현재 진행 중 여부", example = "true")
    private Boolean isCurrent;
}
