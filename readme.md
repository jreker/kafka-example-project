# kafka-example-project 

## getting started
To use this example please follow the steps below:

### Prerequisites
- Java 8
- Docker 

### 1. Start Kafka-Cluster
```
docker-compose up
```

### 2. Create Topic in Kafka
```
kafka-topics.sh --create --topic "customer" --replication-factor 3 --partitions 3 --bootstrap-server=localhost:9092
```

### 3. Start Server System emulations
```
java -Djreker.kafka.topic.name=customer -Djreker.kafka.bootstrap.server=localhost:29092 -Djreker.kafka.client.id=SAP -jar producer/target/producer-0.0.1-SNAPSHOT.jar
```
---
### Additional informations
- Build with Bitnami Kafka Docker Images
- Applications Build with Java Spring Boot for Apache Kafka

