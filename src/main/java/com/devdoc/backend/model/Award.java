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
@Schema(description = "수상 이력 정보를 담는 모델")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Award ID", example = "1")
    private Integer id;

    @Schema(description = "수상명", example = "개근상")
    private String awardName;

    @Schema(description = "수상 기관", example = "00대학교")
    private String awardingInstitution;

    @Schema(description = "수상 날짜", example = "2023-05-01")
    private String date;

    @Schema(description = "수상 설명", example = "수상 내역에 대한 자세한 설명...")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
