package io.materialplus.producer.util;

import io.materialplus.producer.model.Transaction;
import io.materialplus.producer.records.ProducerCreditCardTransactionRecord;

public class MessageTransformer {

    public static Transaction transform(ProducerCreditCardTransactionRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Exception occurred while transforming producer credit card transaction");
        }

        return new Transaction(
                formatTransactionId(String.valueOf(record.transactionId())),
                record.cardNumber(),
                record.amount(),
                record.timestamp(),
                record.merchantId()
        );
    }

    private static int formatTransactionId(String transactionId) {
        try {
            int id = Integer.parseInt(transactionId);
            return Integer.parseInt(String.format("%08d", Math.abs(id))); // Pad with zeros and ensure positive
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid transaction ID format: " + transactionId);
        }
    }
}
