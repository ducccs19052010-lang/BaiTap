package com.uet.auction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bank {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bank.class);
    private static final String ID_NUMBER_PATTERN = "\\d{9}";
    private static final String SPACE_PATTERN = "\\s+";

    private List<Customer> customers;

    public Bank() {
        this.customers = new ArrayList<>();
    }

    public List<Customer> getCustomerList() {
        return customers;
    }

    public void setCustomerList(List<Customer> customerList) {
        if (customerList == null) {
            this.customers = new ArrayList<>();
        } else {
            this.customers = customerList;
        }
    }

    public void readCustomerList(InputStream inputStream) {
        LOGGER.debug("Started reading customer data");

        if (inputStream == null) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            readCustomerLines(reader);
        } catch (IOException | NumberFormatException exception) {
            LOGGER.error("Failed to read customer data", exception);
        }
    }

    private void readCustomerLines(BufferedReader reader) throws IOException {
        String line;
        Customer currentCustomer = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            Customer newCustomer = parseCustomer(line);
            if (newCustomer != null) {
                currentCustomer = newCustomer;
                customers.add(currentCustomer);
                LOGGER.info("Added customer name={}", currentCustomer.getFullName());
                continue;
            }

            if (currentCustomer != null) {
                Account account = parseAccount(line);
                if (account != null) {
                    currentCustomer.addAccount(account);
                }
            }
        }
    }

    private Customer parseCustomer(String line) {
        int lastSpaceIndex = line.lastIndexOf(' ');

        if (lastSpaceIndex <= 0) {
            return null;
        }

        String token = line.substring(lastSpaceIndex + 1).trim();
        if (!token.matches(ID_NUMBER_PATTERN)) {
            return null;
        }

        String fullName = line.substring(0, lastSpaceIndex).trim();
        return new Customer(Long.parseLong(token), fullName);
    }

    private Account parseAccount(String line) {
        String[] parts = line.split(SPACE_PATTERN);

        if (parts.length < 3) {
            return null;
        }

        long accountNumber = Long.parseLong(parts[0]);
        String accountType = parts[1];
        double balance = Double.parseDouble(parts[2]);

        if (Account.CHECKING_TYPE.equals(accountType)) {
            return new CheckingAccount(accountNumber, balance);
        }

        if (Account.SAVINGS_TYPE.equals(accountType)) {
            return new SavingsAccount(accountNumber, balance);
        }

        return null;
    }

    public String getCustomersInfoByIdOrder() {
        List<Customer> sortedCustomers = new ArrayList<>(customers);
        sortedCustomers.sort(Comparator.comparingLong(Customer::getIdNumber));
        return buildCustomerInfo(sortedCustomers);
    }

    public String getCustomersInfoByNameOrder() {
        List<Customer> sortedCustomers = new ArrayList<>(customers);
        sortedCustomers.sort(
                Comparator.comparing(Customer::getFullName)
                        .thenComparingLong(Customer::getIdNumber));
        return buildCustomerInfo(sortedCustomers);
    }

    private String buildCustomerInfo(List<Customer> customerList) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < customerList.size(); index++) {
            builder.append(customerList.get(index).getCustomerInfo());
            if (index < customerList.size() - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}