package dev.rexijie.pipeline.realtime.cleaning.service;


import dev.rexijie.pipeline.realtime.kafka.helpers.DataTransformer;
import dev.rexijie.pipeline.realtime.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RawDataConsumer {
    Logger LOG = LoggerFactory.getLogger(RawDataConsumer.class);
    private final DataTransformer dataTransformer;

    public RawDataConsumer(DataTransformer dataTransformer) {
        this.dataTransformer = dataTransformer;
    }

    @KafkaListener(topics = "${spring.kafka.properties.consume-from}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void onMessage(@Payload Map<String, Object> data) {
        User transform = dataTransformer.transform(data, User.class);
        LOG.info("Received User: {}", transform);
    }
}
