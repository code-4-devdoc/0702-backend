package com.devdoc.backend.controller;

import com.devdoc.backend.dto.PromptRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/kogpt")
@Tag(name = "Kogpt API", description = "Kakao GPT API")
public class KogptController {

    @Value("${kakao.api.key}")
    private String apiKey; // apiKey 변수에 kakao.api.key 값을 주입

    // HTTP 요청 전송하는 Spring 템플릿
    // 외부 HTTP API 호출 수행
    private final RestTemplate restTemplate;

    public KogptController() {
        this.restTemplate = new RestTemplate();
    }

    private static final Logger logger = LoggerFactory.getLogger(KogptController.class);

    @Operation(summary = "텍스트 생성", description = "프롬프트를 기반으로 텍스트를 생성.")
    @PostMapping("/generate") // HTTP 요청 본문을 'PromptRequest' 클래스 인스턴스로 변환
    public ResponseEntity<?> generateText(@RequestBody PromptRequest request) {
        // API 키 유효성 검사
        if (!isValidApiKey()) {
            logger.error("API key is missing or invalid");
            // 유효하지 않으면 403 에러
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("API key is missing or invalid");
        }

        try {
            // 요청 헤더 설정
            String apiUrl = "https://api.kakaobrain.com/v1/inference/kogpt/generation";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "KakaoAK " + apiKey);

            // 요청 본문 구성
            Map<String, Object> payload = new HashMap<>();
            // PromptRequest에서 받은 값으로 요청 데이터 설정
            payload.put("prompt", request.getPrompt());
            payload.put("max_tokens", request.getMaxTokens() != null ? request.getMaxTokens() : 50);
            payload.put("temperature", request.getTemperature() != null ? request.getTemperature() : 0.1);

            // HttpEntity 객체에 헤더와 페이로드 저장
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
            // RestTemplate으로 POST 요청 전송
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

            logger.info("Response from external API: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                // 요청 성공 시 응답의 본문을 Map 객체로 파싱하여 반환
                Map<String, Object> responseMap = parseResponse(response.getBody());
                // 200 상태와 함꼐 결과 반환
                return ResponseEntity.ok(responseMap);
            } else {
                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            }
        } catch (RestClientResponseException e) {
            logger.error("Failed to call external API", e);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Server error occurred. Please try again later.");
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
        }
    }

    // JSON 응답 문자열을 Map<String, Object>로 변환
    private Map<String, Object> parseResponse(String responseBody) {
        return new Gson().fromJson(responseBody, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    // API 키의 존재 여부 검사
    private boolean isValidApiKey() {
        return apiKey != null && !apiKey.trim().isEmpty();
    }
}
