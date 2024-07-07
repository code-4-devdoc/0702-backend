package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

// ResponseDTO<T>: 응답 데이터를 구조화하여 클라이언트에 전송하기 위한 공통 응답 DTO

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "응답 데이터를 구조화하여 클라이언트에 전송하기 위한 공통 응답 DTO")
public class ResponseDTO<T> {

	@Schema(description = "오류 메시지", example = "Invalid request")
	private String error; // 오류 메시지

	@Schema(description = "데이터 리스트")
	private List<T> data; // 데이터 리스트
}

