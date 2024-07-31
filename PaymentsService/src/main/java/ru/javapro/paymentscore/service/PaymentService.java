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
        PayRespDto payResp = restTemplate.postForObject("/execPay", payReq, PayRespDto.class);
        if (payResp.errorCode() != 0) throw new BadReqException(payResp.answer());
        return payResp;
    }
}
