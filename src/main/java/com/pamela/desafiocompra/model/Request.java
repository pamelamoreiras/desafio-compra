package com.pamela.desafiocompra.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {

    private ProductRequest productRequest;
    private PaymentConditionRequest paymentConditionRequest;
}
