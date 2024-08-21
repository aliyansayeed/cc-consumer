package io.materialplus.producer.util;

import io.materialplus.producer.model.Transaction;

import java.util.Objects;

public class MessageValidator {

    public static boolean validate(Transaction transaction) {

        if (Objects.isNull(transaction)) {
            return false;
        }
        return isAmountValid(transaction.getAmount()) && isCardNumberValid(transaction.getCardNumber());
    }

    private static boolean isAmountValid(double amount) {
        return amount >= 0 && amount <= 1000;
    }

    private static boolean isCardNumberValid(String cardNumber) {
        return cardNumber != null && cardNumber.length() == 16;
    }
}
