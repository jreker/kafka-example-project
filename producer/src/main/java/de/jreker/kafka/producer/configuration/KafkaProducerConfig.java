package de.jreker.kafka.producer.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import de.jreker.kafka.producer.Customer;

@Configuration
public class KafkaProducerConfig {

   @Value("${jreker.kafka.bootstrap.server}")
   private String BOOTSTRAP_SERVER;
   @Value("${jreker.kafka.client.id}")
   private String CLIENT_ID;


   @Bean
   public ProducerFactory<String, Customer> producerFactory() {
      Map<String, Object> configProps = new HashMap<>();
      configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
      configProps.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
      return new DefaultKafkaProducerFactory<>(configProps);
   }
   
   @Bean
   public KafkaTemplate<String, Customer> kafkaTemplate() {
      return new KafkaTemplate<String, Customer>(producerFactory());
   }
}