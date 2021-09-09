package dev.rexijie.pipeline.realtime.ingestion.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class StartupStatusPrinter implements ApplicationListener<ApplicationStartedEvent> {
    private final Logger LOG = LoggerFactory.getLogger(StartupStatusPrinter.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        KafkaProperties properties = event.getApplicationContext().getBean(KafkaProperties.class);

        LOG.info("Configured to publish to topic: {}", properties.getProperties().get("produce-to"));
    }
}
