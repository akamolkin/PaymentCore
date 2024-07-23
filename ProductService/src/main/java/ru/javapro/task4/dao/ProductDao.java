package ru.javapro.task4.dao;

import org.springframework.stereotype.Component;
import ru.javapro.task4.entity.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {
    private DataSource ds;
    private static final String getProductById = "select id, user_id, balance, acc_num, product_type FROM products where id = ?";
    private static final String getAllByUserId = "select id, user_id, balance, acc_num, product_type FROM products where user_id = ?";
    private static final String insertProduct = "insert into products(user_id, balance, acc_num, product_type) values(?, ?, ?, ?)";
    private static final String deleteById = "delete from products where id = ?";
    private static final String updateById = "update products set user_id = ?, balance = ?, acc_num = ?, product_type = ? where id = ?";

    public ProductDao(DataSource ds) {
        this.ds = ds;
    }

    public List<Product> findAllByUserId(long userId) {
        List<Product> result = new ArrayList<>();
        
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(getAllByUserId)
        ) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("acc_num"),
                        rs.getDouble("balance"),
                        rs.getString("product_type")
                );
                result.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public Product findById(long id) {
        Product result = null;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(getProductById)
        ) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new Product(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("acc_num"),
                        rs.getDouble("balance"),
                        rs.getString("product_type")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public int add(Product product) {
        int result = -1;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(insertProduct)
        ) {
            stmt.setLong(1, product.getUserId());
            stmt.setDouble(2, product.getBalance());
            stmt.setString(3, product.getAccountNumber());
            stmt.setString(4, product.getType().name());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int deleteById(long id) {
        int result = -1;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(deleteById)
        ) {
            stmt.setLong(1, id);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int updateProduct(Product product){
        int result = -1;
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(updateById)
        ) {
            stmt.setLong(1, product.getUserId());
            stmt.setDouble(2, product.getBalance());
            stmt.setString(3, product.getAccountNumber());
            stmt.setString(4, product.getType().name());
            stmt.setLong(5, product.getId());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
