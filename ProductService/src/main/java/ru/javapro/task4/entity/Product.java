package ru.javapro.task4.entity;

public class Product {
    //Продукт клиента: id, номер счета, баланс, тип продукта (счет, карта).
    private long id;
    private long userId;
    private String accountNumber;
    private double balance;
    private ProductType type;

    public Product(long id, long userId, String accountNumber, double balance, String type) {
        this.id = id;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = ProductType.valueOf(type);
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public ProductType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", userId=" + userId +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                '}';
    }
}
