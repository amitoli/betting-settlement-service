# Sports Betting Settlement Service

A backend application that simulates sports betting event outcome handling and bet settlement via Kafka and RocketMQ.

## Features

- REST API endpoint to publish sports event outcomes to Kafka
- Kafka consumer that processes event outcomes
- Automatic bet matching and settlement
- RocketMQ integration (mocked as per requirements)
- In-memory H2 database for bet storage
- Docker Compose setup for Kafka
- Kafka UI for monitoring

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Docker

## Setup and Running

### 1. Start Kafka with Docker Compose

docker-compose up -d


### 2. Wait for Kafka to be Ready

Wait about 30-60 seconds for Kafka to fully initialize. You can check the logs:

### 3. Run the Application
- mvn clean install
- mvn spring-boot:run


The application will start on `http://localhost:8080`

### 4. Access Kafka UI (Optional)

Access Kafka UI at `http://localhost:8090` to monitor:
- Topics
- Messages
- Consumer groups
- Brokers

## API Usage

### Publish Event Outcome

``
curl -X POST http://localhost:8080/api/events/outcome
-H "Content-Type: application/json"
-d '{
"eventId": "event1",
"eventName": "Team A vs Team B",
"eventWinnerId": "team1"
}'
``

### View Database (H2 Console)

Access the H2 console at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## Docker Commands

