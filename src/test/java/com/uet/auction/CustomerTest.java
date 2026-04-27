package com.uet.auction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests customer behavior.
 */
public class CustomerTest {
    @Test
    void shouldAddAccountToCustomer() {
        Customer customer = new Customer(123456789L, "Nguyen Van A");
        Account account = new CheckingAccount(1001L, 1000.0);

        customer.addAccount(account);

        assertEquals(1, customer.getAccountList().size());
        assertTrue(customer.getAccountList().contains(account));
    }

    @Test
    void shouldReturnCustomerInfo() {
        Customer customer = new Customer(123456789L, "Nguyen Van A");

        String info = customer.getCustomerInfo();

        assertEquals("Số CMND: 123456789. Họ tên: Nguyen Van A.", info);
    }
}