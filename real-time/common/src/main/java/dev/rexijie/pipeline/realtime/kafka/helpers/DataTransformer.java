package dev.rexijie.pipeline.realtime.kafka.helpers;

public interface DataTransformer {
    <T,D> T transform(D data, Class<T> tClass);
}
