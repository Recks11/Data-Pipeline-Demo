package dev.rexijie.pipeline.realtime.kafka.event;

public interface MessageEvent<T> {
    String getKey();
    T getData();
}
