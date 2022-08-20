# kafka-example-project 

## getting started
To use this example please follow the steps below. You need to execute all commands from unix or windows cmd with the project as root directory.

### Prerequisites
- Java 8
- Docker (Tested with Engine 20.10.14 / Docker Desktop 4.8.2)
- Maven (Tested with 3.8.5)

### 1. Start Kafka-Cluster
```
docker-compose up
```

### 2. Create Topic in Kafka
Run the following command in console to create the topic "customer" inside the kafka broker 01.
```
docker exec -it kafka01 kafka-topics.sh --create --topic "customer" --replication-factor 3 --partitions 3 --bootstrap-server=localhost:9092
```

### 3. Compile Java Projects with Maven
Package both Java projects. See below:
```
mvn clean package -f consumer/pom.xml
mvn clean package -f producer/pom.xml
```

### 4. Start Consumer emulations
### SIMPLE
For windows start ```start.cmd``` and for Linux ```start.sh``` from the root directory to start the three consumers.

### MANUALLY
Start each program from different consoles
```
java -Dapp.name="Consumer-SAP" -Djreker.kafka.topic.name=customer -Djreker.kafka.consumer.group.id=sap -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar
```
```
java -Dapp.name="Consumer-CRM-01" -Djreker.kafka.topic.name=customer -Djreker.kafka.consumer.group.id=crm -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar
```
```
java -Dapp.name="Consumer-CRM-02" -Djreker.kafka.topic.name=customer -Djreker.kafka.consumer.group.id=crm -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar
```
### 5. Start Producer Emulations
You can choose if you want to have multiple producers or just one. The function of both is the same.

Start SAP Producer
```
java -Djreker.kafka.topic.name=customer -Djreker.kafka.bootstrap.server=localhost:29092 -Djreker.kafka.client.id=SAP -jar producer/target/producer-0.0.1-SNAPSHOT.jar
```
Start CRM Producer
```
java -Djreker.kafka.topic.name=customer -Djreker.kafka.bootstrap.server=localhost:29092 -Djreker.kafka.client.id=CRM -jar producer/target/producer-0.0.1-SNAPSHOT.jar
```
---
### Additional informations
- Build with Bitnami Kafka Docker Images
- Applications Build with Java Spring Boot for Apache Kafka with Maven

