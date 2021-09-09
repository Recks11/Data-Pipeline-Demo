package dev.rexijie.pipeline.realtime.kafka.messages;

import dev.rexijie.pipeline.realtime.kafka.event.MessageEvent;
import reactor.core.publisher.Mono;

public class KafkaMessage<D> implements MessageEvent<D> {
    private final D data;
    private final String key;
    private final String destTopic;

    public KafkaMessage(D data, String key, String destTopic) {
        this.data = data;
        this.key = key;
        this.destTopic = destTopic;
    }

    public String getDestTopic() {
        return destTopic;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public D getData() {
        return data;
    }

    public static <T> Mono<KafkaMessage<T>> from(T data, String key, String topic) {
        return Mono.just(new KafkaMessage<>(data, key, topic));
    }
}
