package com.uet.auction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SavingsAccount extends Account {
    private static final Logger LOGGER = LoggerFactory.getLogger(SavingsAccount.class);
    private static final double MAX_WITHDRAW_AMOUNT = 1000.0;
    private static final double MIN_BALANCE = 5000.0;

    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        double initialBalance = getBalance();

        try {
            doDepositing(amount);
            double finalBalance = getBalance();

            Transaction transaction = new Transaction(
                    Transaction.TYPE_DEPOSIT_SAVINGS,
                    amount,
                    initialBalance,
                    finalBalance);

            addTransaction(transaction);
            LOGGER.info("Savings deposit successful: amount={}", amount);
        } catch (BankException exception) {
            LOGGER.error("Savings deposit failed", exception);
        }
    }

    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();

        try {
            validateSavingsWithdrawal(amount, initialBalance);
            doWithdrawing(amount);

            double finalBalance = getBalance();
            Transaction transaction = new Transaction(
                    Transaction.TYPE_WITHDRAW_SAVINGS,
                    amount,
                    initialBalance,
                    finalBalance);

            addTransaction(transaction);
            LOGGER.info("Savings withdraw successful: amount={}", amount);
        } catch (BankException exception) {
            LOGGER.error("Savings withdraw failed", exception);
        }
    }

    private void validateSavingsWithdrawal(double amount, double initialBalance)
            throws BankException {
        if (amount > MAX_WITHDRAW_AMOUNT) {
            throw new InvalidFundingAmountException(amount);
        }

        if (initialBalance - amount < MIN_BALANCE) {
            throw new InsufficientFundsException(amount);
        }
    }
}