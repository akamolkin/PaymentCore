package ru.javapro.task4.entity;

public class User {
    private long id;
    private String username;

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ru.javapro.task4.entity.User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
