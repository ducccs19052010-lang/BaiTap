package com.uet.auction;

import java.util.Locale;

public class InvalidFundingAmountException extends BankException {
    public InvalidFundingAmountException(double amount) {
        super("Số tiền không hợp lệ: $" + String.format(Locale.US, "%.2f", amount)); //Locale.US: Quy định dấu . cho phần thập phân
        //%.2f: Lấy 2 chữ số hàng thập phân
    }
}