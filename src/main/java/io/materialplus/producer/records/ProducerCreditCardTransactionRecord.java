package io.materialplus.producer.records;

import io.materialplus.producer.exception.ConsumerInvalidAmountException;
import io.materialplus.producer.util.ProducerAppUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

@Slf4j
public record ProducerCreditCardTransactionRecord(
        int transactionId,
        String cardNumber,
        double amount,
        LocalDateTime timestamp,
        String merchantId
) implements Serializable {

    public ProducerCreditCardTransactionRecord {
        if (!ProducerAppUtils.amountValidation(amount)) {
            log.error("Invalid amount {} for transaction {}", amount, transactionId);
            throw new ConsumerInvalidAmountException("Amount cannot be negative: " + amount);
        }
    }
}
