import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

// import org.apache.kafka.clients.producer.ProducerConfig;

public class KafkaProducerApp {
    public static void main(String[] args) {
        Properties props = new Properties();

        /*
         * The producer connects to the first available broker
         * and uses it to discover the rest of the available brokers of the cluster
         *
         * To see all the configurations possible:
         * https://kafka.apache.org/24/javadoc/org/apache/kafka/clients/producer/ProducerConfig.html
         */
        props.put("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        /*
         * You can also use the ProducerConfig to set the properties for the Consumer.
         * This does exactly the same as the code above and can be used instead and it
         * makes it easier to find the config you are looking for
         */
        /*
         * props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093");
         * props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
         * props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
         */

        KafkaProducer<String, String> producer = new KafkaProducer(props);

        for (int i = 0; i < 50; i++) {
            producer.send(new ProducerRecord<String, String>("my-topic",Integer.toString(i), "Message no: " + i));
        }
        System.out.println("\nThe producer sent the records to the topic\n");

        System.out.println("Closing the Producer");
        producer.close();
    }
}
