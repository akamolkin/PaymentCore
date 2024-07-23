package ru.javapro.task4.dto;

import ru.javapro.task4.entity.User;

import java.util.List;

public record UserListRespDto(
        List<User> userList
) {
}
