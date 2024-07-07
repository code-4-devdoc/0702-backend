package com.devdoc.backend.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "프롬프트 요청을 위한 DTO")
public class PromptRequest {

    @Schema(description = "프롬프트 텍스트", example = "고양이의 평균 수명은?")
    private String prompt;

    @Schema(description = "최대 토큰 수", example = "50")
    private Integer maxTokens;

    @Schema(description = "창의성", example = "0.1")
    private Double temperature;
}
