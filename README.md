## Start a Kafka Cluster
start the zookeeper from within the kafka directory:  

    bin/zookeeper-server-start.sh config/zookeeper.properties

start a single broker:  

    bin/kafka-server-start.sh config/server.properties

## Create a Kafka Topic named "my-topic"
run from within the kafka directory:  

    bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic my-topic--partitions 2 --replication-factor 1

## Check that the topic was created successfully
run from within the kafka directory:  

    bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic my-topic
    
## Run the KafkaConsumerApp and KafkaProducerApp



