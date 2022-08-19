package de.jreker.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Consumer {

    Logger logger = LoggerFactory.getLogger(Consumer.class);
    @KafkaListener(topics = "${jreker.kafka.topic.name}", groupId = "${jreker.kafka.consumer.group.id}")
    public void listenGroupFoo(@Payload Customer message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info("Received Message: " + message.toString() + " from Partition " + partition);
    }
}
