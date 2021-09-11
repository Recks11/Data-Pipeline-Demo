package dev.rexijie.pipeline.realtime.cleaning.service;


import dev.rexijie.pipeline.realtime.kafka.helpers.DataTransformer;
import dev.rexijie.pipeline.realtime.kafka.messages.KafkaMessage;
import dev.rexijie.pipeline.realtime.kafka.model.User;
import dev.rexijie.pipeline.realtime.kafka.service.KafkaMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class RawDataConsumer {
    Logger LOG = LoggerFactory.getLogger(RawDataConsumer.class);
    private final DataTransformer dataTransformer;
    private final KafkaMessageSender<User> sender;

    @Value("${spring.kafka.properties.produce-to}")
    private String toTopic;

    public RawDataConsumer(DataTransformer dataTransformer,
                           KafkaMessageSender<User> sender) {
        this.dataTransformer = dataTransformer;
        this.sender = sender;
    }


    @KafkaListener(topics = "${spring.kafka.properties.consume-from}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "concurrentKafkaListenerContainerFactory",
            errorHandler = "ingestionMessageErrorHandler")
    public void onMessage(@Payload Map<String, Object> data) {
        User transform = dataTransformer.transform(data, User.class);
        LOG.info("Received User: {}", transform);
        KafkaMessage.from(transform, UUID.randomUUID().toString(), toTopic)
                .doOnNext(sender::sendMessage)
                .subscribe();
    }
}
