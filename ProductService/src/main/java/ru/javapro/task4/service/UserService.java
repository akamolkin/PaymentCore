package ru.javapro.task4.service;

import org.springframework.stereotype.Service;
import ru.javapro.task4.dto.AppRespDto;
import ru.javapro.task4.dao.UserDao;
import ru.javapro.task4.entity.User;
import ru.javapro.task4.exception.BadReqException;
import ru.javapro.task4.exception.NotFoundException;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;
    public User getUserById(long id){
        User result = userDao.findById(id);
        if (result == null) throw new NotFoundException("User not found with id " + id);
        return result;
    }
    public User getUserByName(String name){
        User result = userDao.findByName(name);
        if (result == null) throw new NotFoundException("User not found with name " + name);
        return result;
    }

    public List<User> getAllUsers(){
        List<User> userList = userDao.getAll();
        System.out.println(userList);
        return userList;
    }
    public AppRespDto addUser(String name){
        if (userDao.findByName(name) != null) throw new BadReqException("User named " + name + " already exists");
        int result = userDao.createUser(name);
        return new AppRespDto(result, "User added") ;
    }

    public void renameUser(String oldName, String newName){
        if (userDao.findByName(oldName) == null) throw new BadReqException("User named " + oldName + " not found");
        if (userDao.findByName(newName) != null) throw new BadReqException("User named " + newName + " already exists");
        if (userDao.updateByName(oldName, newName) == 1) System.out.println("User renamed");
    }

    public void deleteUser(String name){
        if (userDao.deleteByName(name) == 1) System.out.println("User deleted");
    }
    public void deleteAllUser(){
        if (userDao.deleteAll() == 1) System.out.println("All users deleted");
    }
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
}
