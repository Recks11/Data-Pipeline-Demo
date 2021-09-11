package dev.rexijie.pipeline.realtime.ingestion.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rexijie.pipeline.realtime.kafka.helpers.DataTransformer;
import dev.rexijie.pipeline.realtime.kafka.service.KafkaMessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {

    @Bean
    DataTransformer classTransformer(ObjectMapper objectMapper) {
//        ClassLoader loader = ClassUtils.getDefaultClassLoader();
        return objectMapper::convertValue;
    }

    @Bean
    <T> KafkaMessageSender<?> kafkaMessageSender(KafkaTemplate<String, T> template) {
        return new KafkaMessageSender<>(template);
    }
}
