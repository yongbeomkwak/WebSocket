package com.shook.websock.Swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@Configuration
@EnableWebMvc
class SwaggerConfig {
    @Bean
    fun openApi(): OpenAPI? {
        return OpenAPI()
            .info(
                Info()
                    .version("v1.0.0")
                    .title("Shook 서버")
                    .description("API DOC")
            )
    }
}