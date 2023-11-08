package com.example.music.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//http://localhost:8081/swagger-ui/index.html#/
@OpenAPIDefinition(info = @Info(
        title = "API AndroidMusicApp",
        description = "API Documentation for My Spring Boot Application",
        version = "1.0"
))
public class OpenApiConfig<ApiInfo> {
}

