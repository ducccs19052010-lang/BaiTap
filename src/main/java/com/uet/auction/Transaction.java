package com.uet.auction;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transaction {
    public static final int type_deposit_checking = 1;
    public static final int TYPE_WITHDRAW_CHECKING = 2;
    public static final int TYPE_DEPOSIT_SAVINGS = 3;
    public static final int TYPE_WITHDRAW_SAVINGS = 4;

    private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

    private int type;
    private double amount;
    private double initialBalance;
    private double finalBalance;

    public Transaction(int type, double amount, double initialBalance, double finalBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    public static String getTypeString(int transactionType) {
        switch (transactionType) {
            case type_deposit_checking:
                return "Nạp tiền vãng lai";
            case TYPE_WITHDRAW_CHECKING:
                return "Rút tiền vãng lai";
            case TYPE_DEPOSIT_SAVINGS:
                return "Nạp tiền tiết kiệm";
            case TYPE_WITHDRAW_SAVINGS:
                return "Rút tiền tiết kiệm";
            default:
                return "Không rõ";
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(double finalBalance) {
        this.finalBalance = finalBalance;
    }

    public String getTransactionSummary() {
        LOGGER.debug("Creating transaction summary for type={}", type);

        return "- Kiểu giao dịch: " + getTypeString(type) + ". Số dư ban đầu: $" + formatMoney(initialBalance) + ". Số tiền: $" + formatMoney(amount) + ". Số dư cuối: $" + formatMoney(finalBalance) + ".";
    }

    private String formatMoney(double value) {
        return String.format(Locale.US, "%.2f", value);
    }
}