package ru.javapro.task4.controller;

import org.springframework.web.bind.annotation.*;
import ru.javapro.task4.dto.AppRespDto;
import ru.javapro.task4.entity.Product;
import ru.javapro.task4.dto.ProductListRespDto;
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
    public AppRespDto addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PostMapping(value = "/updateProduct")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

}
