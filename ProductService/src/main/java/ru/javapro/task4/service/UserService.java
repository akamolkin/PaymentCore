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
//        User result = userDao.findById(id);
//        if (result == null) throw new NotFoundException("User not found with id " + id);
        return checkUserById(id);
    }
    public User getUserByName(String name){
        return checkUserByName(name);
//        User result = userDao.findByName(name);
//        if (result == null) throw new NotFoundException("User not found with name " + name);
//        return result;
    }

    public List<User> getAllUsers(){
        //        List<User> userList = userDao.getAll();
//        System.out.println(userList);
        return userRepository.findAll();
    }
    public User addUser(String name){
        Optional<User> userOptional = userRepository.findByUserName(name);
        if (userOptional.isPresent()) throw new BadReqException("User named " + name + " already exists");
        User user = new User();
        user.setUserName(name);
        return userRepository.save(user);
//        if (userDao.findByName(name) != null) throw new BadReqException("User named " + name + " already exists");
//        int result = userDao.createUser(name);
//        return new AppRespDto(result, "User added") ;
    }

    public User renameUser(String oldName, String newName){
        User user = checkUserByName(oldName);
        checkUserExists(newName);
        user.setUserName(newName);
        return userRepository.save(user);

//        if (userDao.findByName(oldName) == null) throw new BadReqException("User named " + oldName + " not found");
//        if (userDao.findByName(newName) != null) throw new BadReqException("User named " + newName + " already exists");
//        if (userDao.updateByName(oldName, newName) == 1) System.out.println("User renamed");
    }

    public void deleteUser(String name){
        userRepository.deleteByUserName(name);
//        if (userDao.deleteByName(name) == 1) System.out.println("User deleted");
    }
    public void deleteAllUser(){
        userRepository.deleteAll();
//        if (userDao.deleteAll() == 1) System.out.println("All users deleted");
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
