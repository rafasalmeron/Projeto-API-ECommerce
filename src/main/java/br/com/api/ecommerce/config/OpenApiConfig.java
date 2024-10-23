package br.com.api.ecommerce.config;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class OpenApiConfig {

    @Bean
    public OperationCustomizer customGlobalResponses() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            operation.getResponses().addApiResponse("200", new ApiResponse().description("Operação bem-sucedida"));
            operation.getResponses().addApiResponse("404", new ApiResponse().description("Erro do Usuário"));
            operation.getResponses().addApiResponse("500", new ApiResponse().description("Erro do Backend"));
            return operation;
        };
    }
}
