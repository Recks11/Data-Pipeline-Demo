package dev.rexijie.pipeline.realtime.cleaning.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rexijie.pipeline.realtime.kafka.helpers.DataTransformer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaProducerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    <T> ProducerFactory<String, T> producerFactory() {
        Map<String, Object> configMap = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(configMap, new StringSerializer(),
                new JsonSerializer<>());
    }

    @Bean
    KafkaTemplate<?, ?> genericKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    DataTransformer classTransformer(ObjectMapper objectMapper) {
        return objectMapper::convertValue;
    }
}
