package ru.javapro.task4.controller;

import org.springframework.web.bind.annotation.*;
import ru.javapro.task4.dto.AppRespDto;
import ru.javapro.task4.dto.UserListRespDto;
import ru.javapro.task4.service.UserService;
import ru.javapro.task4.entity.User;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/getAllUsers")
    public UserListRespDto getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return new UserListRespDto(userList);
    }

    @GetMapping(value = "/getUserById")
    public User getProductById(@RequestParam long userId){
        return userService.getUserById(userId);
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user.getUserName());
    }
}
