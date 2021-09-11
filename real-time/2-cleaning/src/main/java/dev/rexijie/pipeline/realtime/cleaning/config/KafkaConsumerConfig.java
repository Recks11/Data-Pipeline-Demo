package dev.rexijie.pipeline.realtime.cleaning.config;

import dev.rexijie.pipeline.realtime.cleaning.RealTimeCleaningApp;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConsumerConfig {
    private static final Logger LOG = LoggerFactory.getLogger(RealTimeCleaningApp.class);

    private final KafkaProperties kafkaProperties;

    public KafkaConsumerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    ConsumerFactory<String, Object> consumerFactory() {

        Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties();

        var deserializer = new JsonDeserializer<>();
//        deserializer.addTrustedPackages(User.class.getPackageName());

        ErrorHandlingDeserializer<Object> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(deserializer);
        return new DefaultKafkaConsumerFactory<>(consumerProperties, new StringDeserializer(), errorHandlingDeserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, ?> concurrentKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, ?> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
