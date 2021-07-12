package com.anz.banking.accountdetails.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Config for Circuit Breaker to initialize Resilience4J
 */
@Configuration
public class Resilience4JConfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> databaseAccessCircuitBreakerFactory() {

        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .build();


        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig).build(), "databaseAccessCircuitBreaker");

    }
}
