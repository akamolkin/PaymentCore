package ru.javapro.task4.dao;

import org.springframework.stereotype.Component;
import ru.javapro.task4.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    private DataSource ds;
    private static final String getUserId = "select id, username from users where id = ?";
    private static final String getUserName = "select id, username from users where username = ?";
    private static final String getAllUser = "select id, username from users";
    private static final String createUser = "insert into users(username) values(?)";
    private static final String updateUserId = "update users set username = ? where id = ?";
    private static final String updateUserName = "update users set username = ? where username = ?";
    private static final String deleteUserId = "delete from users where id = ?";
    private static final String deleteUserName = "delete from users where username = ?";
    private static final String deleteUserAll = "delete from users";

    public UserDao(DataSource ds) {
        this.ds = ds;
    }

    public User findById(long id) {
        User result = null;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(getUserId)
        ) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new User(rs.getLong("id"), rs.getString("username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public User findByName(String name) {
        User result = null;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(getUserName)
        ) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new User(rs.getLong("id"), rs.getString("username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(getAllUser)
        ) {
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                User user = new User(rs.getLong("id"),
                rs.getString("username"));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int createUser(String name) {
       int result = -1;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(createUser)
        ) {
            stmt.setString(1, name);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int updateById(long id, String newName) {
        int result = -1;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(updateUserId)
        ) {
            stmt.setString(1, newName);
            stmt.setLong(2, id);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int updateByName(String oldName, String newName) {
        int result = -1;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(updateUserName)
        ) {
            stmt.setString(1, newName);
            stmt.setString(2, oldName);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int deleteById(long id) {
        int result = -1;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(deleteUserId)
        ) {
            stmt.setLong(1, id);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int deleteByName(String name) {
        int result = -1;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(deleteUserName)
        ) {
            stmt.setString(1, name);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int deleteAll() {
        int result = -1;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(deleteUserAll)
        ) {
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
