package com.test.banking.entity;
public class BankModel {
    //Defining Variables
    private String currency;
    private double amount;
    //Constructors

    public BankModel(String currency, double amount) {
        super();
        this.currency = currency;
        this.amount = amount;
    }

    public BankModel(){}

    //Getters and Setters
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
