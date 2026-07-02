package br.ufjf.sgcapi.config;

import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration

public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SGC API")
                        .description("API do Sistema de Concessionária")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Pedro Bonfá e Bernardo Bressan")
                                .url("")
                                .email("")
                        )
                )

                .tags(List.of(
                        new Tag().name("Funcionários e Autenticação").description("Gerenciamento de Funcionários e Login"),
                        new Tag().name("Acessórios").description("Gerenciamento de Acessórios"),
                        new Tag().name("Carros Novos").description("Gerenciamento de Carros Novos"),
                        new Tag().name("Carros Usados").description("Gerenciamento de Carros Usados")
                ))

                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }



}
