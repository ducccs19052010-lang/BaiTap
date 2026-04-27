package com.uet.auction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Account {
    public static final String CHECKING_TYPE = "CHECKING";
    public static final String SAVINGS_TYPE = "SAVINGS";

    private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);

    private long accountNumber;
    private double balance;
    private List<Transaction> transactions;
    protected Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactions;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        if (transactionList == null) {
            this.transactions = new ArrayList<>();
        } else {
            this.transactions = transactionList;
        }
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    protected void doDepositing(double amount) throws InvalidFundingAmountException {
        if (amount <= 0) {
            throw new InvalidFundingAmountException(amount);
        }
        balance += amount;
    }

    protected void doWithdrawing(double amount) throws BankException {
        if (amount <= 0) {
            throw new InvalidFundingAmountException(amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
    }

    public String getTransactionHistory() {
        StringBuilder builder = new StringBuilder();
        builder.append("Lịch sử giao dịch của tài khoản ")
                .append(accountNumber)
                .append(":\n");

        for (int index = 0; index < transactions.size(); index++) {
            builder.append(transactions.get(index).getTransactionSummary());
            if (index < transactions.size() - 1) {
                builder.append("\n");
            }
        }

        LOGGER.debug("Transaction history was requested for accountNumber={}", accountNumber);
        return builder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Account)) {
            return false;
        }
        Account account = (Account) object;
        return accountNumber == account.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}