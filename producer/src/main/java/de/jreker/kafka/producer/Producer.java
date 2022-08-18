package de.jreker.kafka.producer;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, Person> kafkaTemplate;

    private String topic = "contacts";

    @EventListener(ApplicationReadyEvent.class)
    public void startSimulate() {
        simulate();
    }
    public void sendMessage(Person data, String key) {
        System.out.println(topic);
        System.out.println(key);
        System.out.println(data);
        ListenableFuture<SendResult<String, Person>> future =
        kafkaTemplate.send(topic, key, data);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Person>>() {
            @Override
            public void onSuccess(SendResult<String, Person> result) {
                System.out.println("Sent message " + data + " with offset " + result.getRecordMetadata().offset());
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Error sending Message: " + ex.getMessage());
            }
        });

    }

    public void runTest() {
        System.out.println(topic);
        System.out.println("Test success");
    }

    public void simulate() {
        try {
            while(true) {
                sendMessage(new Person("Johannes", "Reken", "016099665275", "reker@outlook.com"), "1");
                TimeUnit.SECONDS.sleep(1);
                sendMessage(new Person("Max", "Mustermän", "016099665275", "reker@outlook.com"), "2");
                TimeUnit.SECONDS.sleep(1);
                sendMessage(new Person("Max", "Mustermann", "016099665275", "reker@outlook.com"), "2");
                TimeUnit.SECONDS.sleep(1);
                sendMessage(new Person("John", "Doe", "1245668", "J.Doe@example.com"), "3");
                TimeUnit.SECONDS.sleep(1);
                sendMessage(new Person("Johannes", "Reker", "016099665275", "reker@outlook.com"), "1");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
