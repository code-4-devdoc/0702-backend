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
@Schema(description = "자격증 정보를 담는 모델")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Certificate ID", example = "1")
    private Integer id;

    @Schema(description = "자격증 이름", example = "정보처리기사")
    private String certificateName;

    @Schema(description = "발급 기관", example = "한국산업인력공단")
    private String issuer;

    @Schema(description = "발급 날짜", example = "2023-05-01")
    private String issueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
