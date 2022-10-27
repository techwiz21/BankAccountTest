package com.test.banking.entity;
public class BankModel {
    private String currency;
    private double amount;

    public BankModel(String currency, double amount) {
        super();
        this.currency = currency;
        this.amount = amount;
    }

    public BankModel(){}

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
