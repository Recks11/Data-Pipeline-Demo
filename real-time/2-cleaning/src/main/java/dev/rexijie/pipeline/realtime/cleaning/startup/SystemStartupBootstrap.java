package dev.rexijie.pipeline.realtime.cleaning.startup;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.internals.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

@Component
public class SystemStartupBootstrap implements ApplicationListener<ApplicationStartedEvent> {
    Logger LOG = LoggerFactory.getLogger(SystemStartupBootstrap.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        KafkaProperties properties = event.getApplicationContext().getBean(KafkaProperties.class);
        final String produceTo = properties.getProperties().get("produce-to");
        final String consumeFrom = properties.getProperties().get("consume-from");

        LOG.info("Configured to receive from to topic: {}", consumeFrom);
        LOG.info("Configured to publish to topic: {}", produceTo);

//        NewTopic produceToTopic = TopicBuilder
//                .name(produceTo)
//                .partitions(3)
//                .config(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "2")
//                .config(TopicConfig.RETENTION_BYTES_CONFIG, "-1")
//                .config(TopicConfig.RETENTION_MS_CONFIG, "3600000")
//                .config(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "2097164")
//                .config(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE)
//                .build();

    }
}
