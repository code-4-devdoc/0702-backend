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
@Schema(description = "대외 활동 항목을 담는 DTO")
public class ActivityDTO {

    @Schema(description = "Activity ID", example = "9")
    private Integer id;

    @Schema(description = "활동명", example = "하계 해커톤")
    private String activityName;

    @Schema(description = "조직명", example = "OO대학교")
    private String organizationName;

    @Schema(description = "시작 날짜", example = "2022-01-01")
    private String startDate;

    @Schema(description = "종료 날짜", example = "2023-01-01")
    private String endDate;

    @Schema(description = "현재 진행 중 여부", example = "true")
    private Boolean isCurrent;
}
