package ru.javapro.task4.controller;

import org.springframework.web.bind.annotation.*;
import ru.javapro.task4.dto.*;
import ru.javapro.task4.entity.Product;
import ru.javapro.task4.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/getAllByUserId")
    public ProductListRespDto getAllByUserId(@RequestParam long userId){
        return productService.getAllByUserId(userId);
    }

    @GetMapping(value = "/getProductById")
    public Product getProductById(@RequestParam long productId){
        return productService.getProductById(productId);
    }

    @PostMapping(value = "/addProduct")
    public Product addProduct(@RequestBody ProductReqDto productReq){
        return productService.addProduct(productReq);
    }

    @PostMapping(value = "/updateProduct")
    public Product updateProduct(@RequestBody ProductReqDto productReq){
        return productService.updateProduct(productReq);
    }

    @PostMapping("/execPay")
    public PayRespDto execPay(@RequestBody PayReqDto payReq){
        return productService.execPay(payReq);
    }

}
