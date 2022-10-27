package com.test.banking.entity;
public class BankModel {
    private String currency;
    private double amount;

    public BankModel(Long id, String currency, double amount) {
        super();
        this.currency = currency;
        this.amount = amount;
    }


    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
