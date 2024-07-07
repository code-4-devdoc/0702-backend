package com.devdoc.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "어학 항목을 담는 DTO")
public class LanguageDTO {

    @Schema(description = "Language ID", example = "1")
    private Integer id;

    @Schema(description = "어학", example = "English")
    private String language;

    @Schema(description = "시험명", example = "TOEIC")
    private String testName;

    @Schema(description = "점수", example = "900")
    private String score;

    @Schema(description = "시험 날짜", example = "2023-06-01")
    private String date;
}
