package dev.rexijie.pipeline.realtime.kafka.model;

public record User(String name,
                   int age) {

    @Override
    public String toString() {
        return """
                { "name":"%s", "age":%s }
                """.formatted(name, age);
    }
}
