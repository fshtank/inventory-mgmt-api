package com.rdllc.inventory.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import tools.jackson.databind.ObjectMapper;

/**
 * Loads a JSON secret from AWS Secrets Manager and contributes each key as a
 * Spring property. Uses ApplicationContextInitializer to ensure properties
 * are available before any beans (including the DataSource) are created.
 */
public class SecretsManagerContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String PROPERTY_SOURCE = "aws-secretsmanager";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        // The secret's id/ARN is a pointer, not a secret itself.
        String secretId = environment.getProperty("aws.secretsmanager.secret-id",
                System.getenv("INVENTORY_SECRET_ID"));

        if (secretId == null || secretId.isBlank()) {
            return; // not running in an AWS environment -> skip
        }

        Region region = environment.getProperty("aws.region") != null
                ? Region.of(environment.getProperty("aws.region"))
                : null;

        try (SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build()) {

            GetSecretValueResponse secret = client.getSecretValue(
                    GetSecretValueRequest.builder().secretId(secretId).build());

            Map<String, Object> properties = parseJson(secret.secretString());
            if (!properties.isEmpty()) {
                // addFirst so fetched values win over any static defaults
                environment.getPropertySources().addFirst(new MapPropertySource(PROPERTY_SOURCE, properties));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> parseJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> properties =
                    mapper.convertValue(mapper.readTree(json), Map.class);
            return properties == null ? new LinkedHashMap<>() : properties;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse secrets-manager payload as JSON", e);
        }
    }
}
