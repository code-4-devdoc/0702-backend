package com.devdoc.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "자격증 항목을 담는 DTO")
public class CertificateDTO {

    @Schema(description = "Certificate ID", example = "1")
    private Integer id;

    @Schema(description = "자격증 이름", example = "정보처리기사")
    private String certificateName;

    @Schema(description = "발급 기관", example = "한국산업인력공단")
    private String issuer;

    @Schema(description = "발급 날짜", example = "2023-05-01")
    private String issueDate;
}
