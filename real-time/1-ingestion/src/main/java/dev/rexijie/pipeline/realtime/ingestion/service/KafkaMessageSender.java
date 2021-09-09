package dev.rexijie.pipeline.realtime.ingestion.service;

import dev.rexijie.pipeline.realtime.kafka.messages.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageSender<T> implements MessageSender<KafkaMessage<T>> {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageSender.class);

    final
    KafkaTemplate<String, T> publisher;

    public KafkaMessageSender(KafkaTemplate<String, T> publisher) {
        this.publisher = publisher;
    }

    @Override
    public void sendMessage(KafkaMessage<T> message) {
        publisher.send(message.getDestTopic(), message.getKey(), message.getData())
                .addCallback(
                        successResult -> LOG.info("Sent message to topic [%s]".formatted(message.getDestTopic())),
                        failureResult -> LOG.error("Failed to send message to topic [%s] due to [%s]"
                                .formatted(message.getDestTopic(), failureResult.getMessage())));
    }
}
