package de.jreker.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Consumer {

    @KafkaListener(topics = "contacts", groupId = "mygroup")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }


}
