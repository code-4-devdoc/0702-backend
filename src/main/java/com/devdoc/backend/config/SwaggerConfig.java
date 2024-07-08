package com.devdoc.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DevDoc API")
                        .version("0.0.1")
                        .description("Spring Boot 기반의 DevDoc 프로젝트에 대한 API 문서입니다."))
                .addServersItem(new Server().url("https://api.dev-doc.co.kr"))
                .addServersItem(new Server().url("http://localhost:8080"));
    }
}
