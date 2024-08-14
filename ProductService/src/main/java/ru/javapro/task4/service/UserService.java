package ru.javapro.task4.service;

import org.springframework.stereotype.Service;
import ru.javapro.task4.entity.User;
import ru.javapro.task4.exception.BadReqException;
import ru.javapro.task4.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id){
        return checkUserById(id);
    }
    public User getUserByName(String name){
        return checkUserByName(name);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User addUser(String name){
        Optional<User> userOptional = userRepository.findByUserName(name);
        if (userOptional.isPresent()) throw new BadReqException("User named " + name + " already exists");
        User user = new User();
        user.setUserName(name);
        return userRepository.save(user);
    }

    public User renameUser(String oldName, String newName){
        User user = checkUserByName(oldName);
        checkUserExists(newName);
        user.setUserName(newName);
        return userRepository.save(user);
    }

    public void deleteUser(String name){
        userRepository.deleteByUserName(name);
    }
    public void deleteAllUser(){
        userRepository.deleteAll();
    }

    public User checkUserById(long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) throw new BadReqException("User not found with id " + userId);
        return userOptional.get();
    }

    public User checkUserByName(String name){
        Optional<User> userOptional = userRepository.findByUserName(name);
        if (userOptional.isEmpty()) throw new BadReqException("User not found with name " + name);
        return userOptional.get();
    }

    public void checkUserExists(String name){
        Optional<User> userOptional = userRepository.findByUserName(name);
        if (userOptional.isPresent()) throw new BadReqException("User named " + name + " already exists");;
    }
}
