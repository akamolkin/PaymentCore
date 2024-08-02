package ru.javapro.task4.service;

import org.springframework.stereotype.Service;
import ru.javapro.task4.dto.*;
import ru.javapro.task4.entity.Product;
import ru.javapro.task4.entity.ProductType;
import ru.javapro.task4.entity.User;
import ru.javapro.task4.exception.BadReqException;
import ru.javapro.task4.exception.NotFoundException;
import ru.javapro.task4.repo.ProductRepository;
import ru.javapro.task4.repo.ProductTypeRepository;
import ru.javapro.task4.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    //private final UserRepository userRepository;
    private final ProductTypeRepository productTypeRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, /*UserRepository userRepository,*/ ProductTypeRepository productTypeRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
       // this.userRepository = userRepository;
        this.productTypeRepository = productTypeRepository;
    }

    public ProductListRespDto getAllByUserId(long userId){
        User user = userService.checkUserById(userId);

//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isEmpty()) throw new BadReqException("User not found with id " + userId);
//        User user = userOptional.get();

//        User user = userDao.findById(userId);
//        if (user == null) throw new BadReqException("User not found with id " + userId);
        List<Product> productList = productRepository.findAllByUserId(userId);

        //List<Product> productList = productDao.findAllByUserId(userId);
        return new ProductListRespDto(productList);
    }

    public Product getProductById(long productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) throw new NotFoundException("Product not found with id " + productId);

//        Product product = productDao.findById(productId);
//        if (product == null) throw new NotFoundException("Product not found with id " + productId);
        return productOptional.get();
    }

    public Product addProduct(ProductReqDto productReq) {
        User user = userService.checkUserById(productReq.userId());

//        Optional<User> userOptional = userRepository.findById(productReq.userId());
//        if (userOptional.isEmpty()) throw new BadReqException("User not found with id " + product.getUserId());
//        User user = userOptional.get();

        ProductType productType = checkProductType(productReq.type());

//        Optional<ProductType> productTypeOptional = productTypeRepository.findByName(productReq.type());
//        if (productTypeOptional.isEmpty()) throw new BadReqException("Type not found with name " + productReq.type());
//        ProductType productType = productTypeOptional.get();

        Product product = new Product();
        product.setUser(user);
        product.setBalance(productReq.balance());
        product.setAccountNumber(productReq.accountNumber());
        product.setType(productType);

        return productRepository.save(product);

//        User user = userDao.findById(product.getUser().getId());
//        if (user == null) throw new BadReqException("User not found with id " + product.getUserId());
//        int result = productDao.add(product);
//        return new AppRespDto(result,"Product added");
    }

    public Product updateProduct(ProductReqDto productReq){

        Product product = getProductById(productReq.id());
//        Optional<Product> productOptional = productRepository.findById(productReq.id());
//        if (productOptional.isEmpty()) throw new BadReqException("Product not found with id " + productReq.id());
//        Product product = productOptional.get();

        User user = userService.checkUserById(productReq.userId());
        ProductType productType = checkProductType(productReq.type());

        product.setUser(user);
        product.setBalance(productReq.balance());
        product.setAccountNumber(productReq.accountNumber());
        product.setType(productType);

        return productRepository.save(product);

//        User user = userDao.findById(product.getUserId());
//        if (user == null) throw new BadReqException("User not found with id " + product.getUserId());
//        int result = productDao.updateProduct(product);
//        return product;
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

        Product productNew = productRepository.save(product);
        return new PayRespDto(productNew.getId(), oldBalance, productNew.getBalance(), 0, "DONE");

//        int result = productDao.updateProduct(product);
//        if (result == 1) return new PayRespDto(product.getId(), oldBalance, newBalance, 0, "DONE");
//        throw new BadReqException("Update error! product = " + product.getId() + " balance = " + oldBalance);
    }

//    public User checkUser(long userId){
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isEmpty()) throw new BadReqException("User not found with id " + userId);
//        return userOptional.get();
//    }

    public ProductType checkProductType(String name){
        Optional<ProductType> productTypeOptional = productTypeRepository.findByName(name);
        if (productTypeOptional.isEmpty()) throw new BadReqException("Type not found with name " + name);
        return productTypeOptional.get();
    }
}
