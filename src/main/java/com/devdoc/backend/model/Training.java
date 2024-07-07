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
@Schema(description = "교육 이수 정보를 담는 모델")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Training ID", example = "1")
    private Integer id; //pk

    @Schema(description = "교육명", example = "[카카오x구름] 자바 스프링 & 리액트 풀스택 개발자 성장 과정")
    private String courseName; //not null

    @Schema(description = "교육 기관명", example = "(주)구름")
    private String institution;

    @Schema(description = "시작 날짜", example = "2023-01-01")
    private String startDate;

    @Schema(description = "종료 날짜", example = "2023-06-01")
    private String endDate;

    @Schema(description = "현재 진행 중 여부", example = "true")
    private Boolean isCurrent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
