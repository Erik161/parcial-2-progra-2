package com.example.helloworld_swagger.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;




public class TipoCambioRangoMonedaConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Tipo de Cambio Rango Moneda")
                    .version("1.0")
                    .description("API para obtener tipo de cambio Rango Moneda"));
    }
}
