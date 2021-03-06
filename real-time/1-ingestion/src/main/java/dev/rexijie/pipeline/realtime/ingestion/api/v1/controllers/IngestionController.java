package dev.rexijie.pipeline.realtime.ingestion.api.v1.controllers;

import dev.rexijie.pipeline.realtime.ingestion.service.MessageSender;
import dev.rexijie.pipeline.realtime.kafka.messages.KafkaMessage;
import dev.rexijie.pipeline.realtime.ingestion.model.ApiResponse;
import dev.rexijie.pipeline.realtime.ingestion.service.KafkaMessageSender;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

import static dev.rexijie.pipeline.realtime.kafka.constants.Endpoints.API_V1_BASE;

@RestController
@RequestMapping(API_V1_BASE + "ingest")
public class IngestionController {

    private final MessageSender<KafkaMessage<Map<String, Object>>> publisher;
    private final String toTopic;

    public IngestionController(MessageSender<KafkaMessage<Map<String, Object>>> publisher,
                               KafkaProperties properties) {
        this.publisher = publisher;
        this.toTopic = properties.getProperties().get("produce-to");
    }

    @PostMapping()
    public Mono<ApiResponse> ingestData(@RequestBody Map<String, Object> body) {
        return KafkaMessage.from(body, UUID.randomUUID().toString(), toTopic)
                .doOnNext(publisher::sendMessage)
                .map(data -> ApiResponse.ok());
    }

}
