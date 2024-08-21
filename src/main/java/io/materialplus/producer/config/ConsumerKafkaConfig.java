package io.materialplus.producer.config;
import io.materialplus.producer.records.ProducerCreditCardTransactionRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerKafkaConfig {

    public static final  String GROUP_ID = "Group100";

    @Bean
    public ConsumerFactory<String, ProducerCreditCardTransactionRecord> consumerFactory() {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
      /*  props.put("security.protocol", "SSL");
        props.put("ssl.truststore.location", "C:\\softwares\\kafka\\ssl2\\kafka.server.truststore.jks");
        props.put("ssl.truststore.password", "123456");
        props.put("ssl.keystore.location", "C:\\softwares\\kafka\\ssl2\\kafka.server.keystore.jks");
        props.put("ssl.keystore.password", "123456");
        props.put("ssl.key.password", "123456");*/
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new  JsonDeserializer<>(ProducerCreditCardTransactionRecord.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProducerCreditCardTransactionRecord> ccTransactionListener()

    {
        ConcurrentKafkaListenerContainerFactory<String, ProducerCreditCardTransactionRecord> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
        //return new KafkaTemplate<>(producerFactory());
    }
}
