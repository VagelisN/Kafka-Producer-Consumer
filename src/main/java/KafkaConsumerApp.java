// import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

// import org.apache.kafka.common.TopicPartition;
// import java.util.ArrayList;

public class KafkaConsumerApp {
    public static void main(String[] args) {

        /*
         * The consumer connects to the first available broker
         * and uses it to discover the rest of the available brokers of the cluster
         *
         * To see all the configurations possible:
         * https://kafka.apache.org/24/javadoc/org/apache/kafka/clients/consumer/ConsumerConfig.html
         */
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "SimpleConsumer");


        /*
        * You can also use the ConsumerConfig to set the properties for the Consumer.
        * This does exactly the same as the code above and can be used instead and it
        * makes it easier to find the config you are looking for
        */
        /*
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"SimpleConsumer");
         */


        KafkaConsumer<String, String> consumer = new KafkaConsumer(props);

        /*
         * To Subscribe to multiple topics we cannot use multiple subscribes
         * as each subscribe call cancels the previous one
         *
         * with the default subscribe() call the consumer subscribes to all partitions
         */

         // For multiple topics:
         /*
        ArrayList<String> topics = new ArrayList<String>();
        topics.add("myTopic1")
        topics.add("myTopic2")
        consumer.subscribe(topics)
         */

         // To subscribe to a particular partition use assign()
        /*
        TopicPartition partition0 = new TopicPartition("my-topic", 0);
        ArrayList<TopicPartition> partitions = new ArrayList<TopicPartition>();
        partitions.add(partition0);
        consumer.assign(partitions);
        */

        consumer.subscribe(Arrays.asList("my-topic"));

        System.out.println("\nThe Consumer started and is waiting for records\n");
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Received record with Key: " + record.key() +
                                   " Value: " + record.value() +
                                   " from Topic: " + record.topic() +
                                   " partition: " + record.partition() +
                                   " offset: " + record.offset());
            }
        }
    }
}
