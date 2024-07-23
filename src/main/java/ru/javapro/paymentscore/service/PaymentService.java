package ru.javapro.paymentscore.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import ru.javapro.paymentscore.dto.PayReqDto;
import ru.javapro.paymentscore.dto.PayRespDto;
import ru.javapro.paymentscore.dto.ProductDto;
import ru.javapro.paymentscore.exception.BadReqException;

@Service
public class PaymentService {
    private final RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductDto getProductById(long productId){
        return restTemplate.getForObject("/getProductById?productId={id}", ProductDto.class, productId);
    }

    public PayRespDto execPay(PayReqDto payReq) {
        ProductDto productDto = getProductById(payReq.productId());
        double oldBalance = productDto.balance();
        if (oldBalance == 0 || payReq.sumPay() > oldBalance) {
            throw new BadReqException("Not enough funds! product = " + productDto.id() + " balance = " + oldBalance);
        }
        double newBalance = oldBalance - payReq.sumPay();
        ProductDto productDtoNew = new ProductDto(productDto.id(), productDto.userId(), productDto.accountNumber(), newBalance, productDto.type());
        productDtoNew = restTemplate.postForObject("/updateProduct", productDtoNew, ProductDto.class);

        PayRespDto payResp = new PayRespDto(productDto.id(), oldBalance, newBalance, "DONE");
        return payResp;
    }
}
