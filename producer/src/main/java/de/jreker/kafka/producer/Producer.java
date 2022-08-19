package de.jreker.kafka.producer;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Value("${jreker.kafka.topic.name}")
    private String TOPIC;

    @EventListener(ApplicationReadyEvent.class)
    public void startSimulate() {
        simulate();
    }

    public void sendMessage(Customer data) {
        System.out.println("--------------------");
        System.out.println("Try to send data to Kafka Server");

        ListenableFuture<SendResult<String, Customer>> future = kafkaTemplate.send(TOPIC, String.valueOf(data.getID()), data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Customer>>() {
            @Override
            public void onSuccess(SendResult<String, Customer> result) {
                System.out.println("Message send successful " + data.toString() + " with offset " + result.getRecordMetadata().offset() + " to partition " + result.getRecordMetadata().partition());
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Error sending Message: " + ex.getMessage());
            }
        });
    }

    public void waitForKeyPressed() {
        System.out.println("Press Enter key to continue...");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }

    public void simulate() {

        while(true) {
        /*sendMessage(new Customer("Johannes", "Reken", "02345987", "reker@outlook.com", 1), "1");
        waitForKeyPressed();
        sendMessage(new Customer("Max", "Mustermän", "468788528", "max@mustermann.de",2), "2");
        waitForKeyPressed();
        sendMessage(new Customer("Max", "Mustermann", "468788528", "reker@outlook.com", 2), "2");
        waitForKeyPressed();
        sendMessage(new Customer("John", "Doe", "1245668", "J.Doe@example.com", 3), "3");
        waitForKeyPressed();
        sendMessage(new Customer("Johannes", "Reker", "02345987", "reker@outlook.com", 1), "1");
        waitForKeyPressed();*/
            Scanner s = new Scanner(System.in);
            System.out.println("Press any key to add or change a new customer or CTRL+C to quit.");
            s.nextLine();
            Customer newCustomer = new Customer();
            System.out.println("Firstname:");
            newCustomer.setFirstName(s.nextLine());
            System.out.println("Lastname:");
            newCustomer.setLastName(s.nextLine());
            System.out.println("Telephone Number:");
            newCustomer.setTelephone(s.nextLine());
            System.out.println("E-Mail Address:");
            newCustomer.setEMail(s.nextLine());
            System.out.println("ID (INT):");
            newCustomer.setID(s.nextInt());

            sendMessage(newCustomer);
        }
    }
}
