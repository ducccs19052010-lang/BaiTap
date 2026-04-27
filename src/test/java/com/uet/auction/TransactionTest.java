package com.uet.auction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests transaction behavior.
 */
public class TransactionTest {
    @Test
    void shouldReturnDepositCheckingTypeString() {
        String result = Transaction.getTypeString(Transaction.TYPE_DEPOSIT_CHECKING);

        assertEquals("Nạp tiền vãng lai", result);
    }

    @Test
    void shouldReturnTransactionSummary() {
        Transaction transaction =
                new Transaction(Transaction.TYPE_DEPOSIT_CHECKING, 100.0, 500.0, 600.0);

        String summary = transaction.getTransactionSummary();

        assertEquals(
                "- Kiểu giao dịch: Nạp tiền vãng lai. "
                        + "Số dư ban đầu: $500.00. "
                        + "Số tiền: $100.00. "
                        + "Số dư cuối: $600.00.",
                summary);
    }
}