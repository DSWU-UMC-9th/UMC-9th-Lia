package com.example.umc9th.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "UMC9th API",
                description = "UMC9th 프로젝트 API 명세서",
                version = "v1"
        )
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                // 모든 URL 다 스캔
                .pathsToMatch("/**")
                // 기준 패키지 전체 스캔 (Umc9thApplication 패키지 기준)
                .packagesToScan("com.example.umc9th")
                .build();
    }
}
