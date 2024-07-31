package ru.javapro.paymentscore.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations.clients")
public class ProductServiceProperties {
    private final RestTemplateProperties productServiceClient;

    @ConstructorBinding
    public ProductServiceProperties(RestTemplateProperties productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    public RestTemplateProperties getRestTemplateProperties() {
        return productServiceClient;
    }
}
