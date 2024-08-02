package ru.javapro.task4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String userName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Product> products;

    @Override
    public String toString() {
        return "ru.javapro.task4.entity.User{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                '}';
    }
}
