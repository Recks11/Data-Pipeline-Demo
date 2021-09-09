package dev.rexijie.pipeline.realtime.ingestion.service;

import dev.rexijie.pipeline.realtime.kafka.event.MessageEvent;

public interface MessageSender<T extends MessageEvent<?>> {
    void sendMessage(T message);
}
