package dev.rexijie.pipeline.realtime.cleaning.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Error Handler class
 */
@Component
public class IngestionMessageErrorHandler implements KafkaListenerErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(IngestionMessageErrorHandler.class);

    @Override
    public Object handleError(@NonNull Message<?> message, @NonNull ListenerExecutionFailedException exception) {
        LOG.error("Error consuming message: {}", message);
        return message;
    }
}
