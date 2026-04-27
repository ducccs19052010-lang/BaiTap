package com.uet.auction;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Account account = new SavingsAccount(123456789L, 10000.0);

        account.deposit(500.0);
        account.withdraw(200.0);
        account.withdraw(20000.0);

        System.out.println(account.getTransactionHistory());
    }
}