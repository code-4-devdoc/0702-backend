package com.devdoc.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "프로필 항목을 담는 DTO")
public class AboutMeDTO {

    @Schema(description = "AboutMe ID", example = "1")
    private Integer id;

    @Schema(description = "사진 URL", example = "http://example.com/photo.jpg")
    private String photo;

    @Schema(description = "이름", example = "김구름")
    private String name;

    @Schema(description = "생년월일", example = "1999-09-13")
    private String birthday;

    @Schema(description = "GitHub URL", example = "https://github.com/kimgoorom")
    private String github;

    @Schema(description = "이메일", example = "example1@example.com")
    private String email;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;

    @Schema(description = "블로그 URL", example = "https://blog.example.com")
    private String blog;

    @Schema(description = "자기소개", example = "자신에 대한 간략한 소개..")
    private String introduction;
}
