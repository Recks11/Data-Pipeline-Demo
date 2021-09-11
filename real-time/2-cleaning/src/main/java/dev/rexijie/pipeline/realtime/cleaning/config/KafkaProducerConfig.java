package dev.rexijie.pipeline.realtime.cleaning.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rexijie.pipeline.realtime.kafka.helpers.DataTransformer;
import dev.rexijie.pipeline.realtime.kafka.service.KafkaMessageSender;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {

    @Bean
    DataTransformer classTransformer(ObjectMapper objectMapper) {
        return objectMapper::convertValue;
    }

    @Bean
    <T> KafkaMessageSender<?> kafkaMessageSender(KafkaTemplate<String, T> template, KafkaProperties props) {
        return new KafkaMessageSender<>(template);
    }
}
