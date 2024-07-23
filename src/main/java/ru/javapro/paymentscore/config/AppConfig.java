package ru.javapro.paymentscore.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.javapro.paymentscore.config.properties.ProductServiceProperties;
import ru.javapro.paymentscore.config.properties.RestTemplateProperties;

@Configuration
@EnableConfigurationProperties(ProductServiceProperties.class)
public class AppConfig {
    @Bean
    public RestTemplate getRestTemplate(
            RestTemplateRespErrorHandler errorHandler,
            ProductServiceProperties productServiceProperties
    ){
        RestTemplateProperties restTemplateProperties = productServiceProperties.getRestTemplateProperties();
        return new RestTemplateBuilder()
                .rootUri(restTemplateProperties.getUrl())
                .setConnectTimeout(restTemplateProperties.getConnectTimeout())
                .setReadTimeout(restTemplateProperties.getConnectTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
