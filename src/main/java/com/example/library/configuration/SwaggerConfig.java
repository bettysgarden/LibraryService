package com.example.library.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${application-description}")
    private String appDescription;

    @Value("${application-version}")
    private String appVersion;

    @Bean
    public GroupedOpenApi usersApi() {
        return GroupedOpenApi.builder()
                .group("Users")
                .pathsToMatch("/api/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi booksApi() {
        return GroupedOpenApi.builder()
                .group("Books")
                .pathsToMatch("/api/books/**")
                .build();
    }
    @Bean
    public GroupedOpenApi authorsApi() {
        return GroupedOpenApi.builder()
                .group("Authors")
                .pathsToMatch("/api/authors/**")
                .build();
    }
    @Bean
    public GroupedOpenApi commentsApi() {
        return GroupedOpenApi.builder()
                .group("Comments")
                .pathsToMatch("/api/comments/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library API")
                        .version(appVersion)
                        .description(appDescription)
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                        .contact(new Contact().name("Your Name").email("your-email@example.com")));
    }
}

