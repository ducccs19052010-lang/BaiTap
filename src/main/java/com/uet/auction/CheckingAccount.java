package com.uet.auction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckingAccount extends Account {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckingAccount.class);

    public CheckingAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        double initialBalance = getBalance();

        try {
            doDepositing(amount);
            double finalBalance = getBalance();

            Transaction transaction = new Transaction(
                    Transaction.type_deposit_checking,
                    amount,
                    initialBalance,
                    finalBalance);

            addTransaction(transaction);
            LOGGER.info("Checking deposit successful: amount={}", amount);
        } catch (BankException exception) {
            LOGGER.error("Checking deposit failed", exception);
        }
    }

    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();

        try {
            doWithdrawing(amount);
            double finalBalance = getBalance();

            Transaction transaction = new Transaction(
                    Transaction.TYPE_WITHDRAW_CHECKING,
                    amount,
                    initialBalance,
                    finalBalance);

            addTransaction(transaction);
            LOGGER.info("Checking withdraw successful: amount={}", amount);
        } catch (BankException exception) {
            LOGGER.error("Checking withdraw failed", exception);
        }
    }
}