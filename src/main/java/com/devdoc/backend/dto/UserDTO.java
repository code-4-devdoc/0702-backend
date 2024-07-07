package com.devdoc.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// UserDTO: 사용자 DTO

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 정보와 인증 토큰을 담는 DTO")
public class UserDTO {

	@Schema(description = "사용자 고유 ID", example = "1")
	private String id;

	@Schema(description = "JWT 토큰", example = "eyJhbXVCJ9...")
	private String token;

	@Schema(description = "닉네임", example = "mynickname")
	private String username;

	@Schema(description = "비밀번호", example = "mypassword11!")
	private String password;

	@Schema(description = "이메일", example = "example1@example.com")
	private String email;

	@Schema(description = "생성일자", example = "2024-07-05T10:15:30")
	private LocalDateTime createdAt;
}
