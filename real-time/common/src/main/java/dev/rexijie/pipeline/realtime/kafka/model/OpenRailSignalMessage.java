package dev.rexijie.pipeline.realtime.kafka.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record OpenRailSignalMessage(String id,
                                    String message,
                                    long time,
                                    String areaId,
                                    String msgType,
                                    String address,
                                    String data,
                                    String description) {

    private LocalDateTime getFormattedTime() {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.of("UTC"));
    }
}
