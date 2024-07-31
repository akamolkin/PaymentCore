package ru.javapro.paymentscore.controller;

import org.springframework.web.bind.annotation.*;
import ru.javapro.paymentscore.dto.PayReqDto;
import ru.javapro.paymentscore.dto.PayRespDto;
import ru.javapro.paymentscore.dto.ProductDto;
import ru.javapro.paymentscore.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getProductById")
    public ProductDto getProductById(@RequestParam long productId){
        return paymentService.getProductById(productId);
    }

    @PostMapping("/execPay")
    public PayRespDto execPay(@RequestBody PayReqDto payReq){
        return paymentService.execPay(payReq);
    }
}
