package ru.javapro.task4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "acc_num")
    private String accountNumber;

    @Column(name = "balance")
    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType type;

//    public Product(long id, long userId, String accountNumber, double balance, String type) {
//        this.id = id;
//        this.userId = userId;
//        this.accountNumber = accountNumber;
//        this.balance = balance;
//        this.type = ProductType.valueOf(type);
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
          //      ", userId=" + userId +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
           //     ", type=" + type +
                '}';
    }
}
