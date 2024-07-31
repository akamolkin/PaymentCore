package ru.javapro.task4.service;

import org.springframework.stereotype.Service;
import ru.javapro.task4.dao.ProductDao;
import ru.javapro.task4.dto.PayReqDto;
import ru.javapro.task4.dto.PayRespDto;
import ru.javapro.task4.dto.ProductListRespDto;
import ru.javapro.task4.dto.AppRespDto;
import ru.javapro.task4.dao.UserDao;
import ru.javapro.task4.entity.Product;
import ru.javapro.task4.entity.User;
import ru.javapro.task4.exception.BadReqException;
import ru.javapro.task4.exception.NotFoundException;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;
    private final UserDao userDao;

    public ProductService(ProductDao productDao, UserDao userDao) {
        this.productDao = productDao;
        this.userDao = userDao;
    }

    public ProductListRespDto getAllByUserId(long userId){
        User user = userDao.findById(userId);
        if (user == null) throw new BadReqException("User not found with id " + userId);

        List<Product> productList = productDao.findAllByUserId(userId);
        return new ProductListRespDto(productList);
    }

    public Product getProductById(long productId){
        Product product = productDao.findById(productId);
        if (product == null) throw new NotFoundException("Product not found with id " + productId);
        return product;
    }

    public AppRespDto addProduct(Product product) {
        User user = userDao.findById(product.getUserId());
        if (user == null) throw new BadReqException("User not found with id " + product.getUserId());
        int result = productDao.add(product);
        return new AppRespDto(result,"Product added");
    }

    public Product updateProduct(Product product){
        User user = userDao.findById(product.getUserId());
        if (user == null) throw new BadReqException("User not found with id " + product.getUserId());
        int result = productDao.updateProduct(product);
        return product;
    }

    public PayRespDto execPay(PayReqDto payReq) {
        Product product = getProductById(payReq.productId());
        double oldBalance = product.getBalance();
        if (oldBalance == 0 || payReq.sumPay() > oldBalance) {
            return new PayRespDto(product.getId(), oldBalance, oldBalance, 1, "Not enough funds! product = " + product.getId() + " balance = " + oldBalance);
            //throw new BadReqException("Not enough funds! product = " + product.getId() + " balance = " + oldBalance);
        }
        double newBalance = oldBalance - payReq.sumPay();
        product.setBalance(newBalance);
        int result = productDao.updateProduct(product);
        if (result == 1) return new PayRespDto(product.getId(), oldBalance, newBalance, 0, "DONE");
        throw new BadReqException("Update error! product = " + product.getId() + " balance = " + oldBalance);
    }
}
