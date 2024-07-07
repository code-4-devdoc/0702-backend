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
@Schema(description = "대외 활동 정보를 담는 모델")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}

