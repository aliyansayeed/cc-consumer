package io.materialplus.producer.service;
import io.materialplus.producer.model.Transaction;
import io.materialplus.producer.records.ProducerCreditCardTransactionRecord;
import io.materialplus.producer.repository.ConsumerRepository;
import io.materialplus.producer.util.MessageTransformer;
import io.materialplus.producer.util.MessageValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @KafkaListener(topics = "prod-msg", groupId = "Group100", containerFactory = "ccTransactionListener")
    public void handleMessage(ProducerCreditCardTransactionRecord record) {
        try {
            Transaction transaction = MessageTransformer.transform(record);
            if (MessageValidator.validate(transaction)) {
                consumerRepository.save(transaction);
            } else {
                log.warn("Invalid transaction record: {}", record);
            }
        } catch (Exception e) {
            log.error("Error processing record: {}", record, e);
        }
    }

}
