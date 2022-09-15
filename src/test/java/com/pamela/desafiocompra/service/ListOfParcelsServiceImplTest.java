package com.pamela.desafiocompra.service;

import com.pamela.desafiocompra.model.PaymentConditionRequest;
import com.pamela.desafiocompra.model.ProductRequest;
import com.pamela.desafiocompra.model.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ListOfParcelsServiceImplTest {

    @InjectMocks
    ListOfParcelsServiceImpl listService;

    @Test
    void quandoReceberOsDadosDaCompraEParcelaForMenorQueSeisRetornaUmaListaDeParcelas() {

        final ProductRequest productRequestToBuild = getProductRequest();

        final var paymentConditionRequestToBuild = PaymentConditionRequest.builder()
                .inputValue(0.0)
                .numberOfParcels(5)
                .build();

        final Request requestToBuild = getRequest(productRequestToBuild, paymentConditionRequestToBuild);

        final var result = listService.execute(requestToBuild);

        Assertions.assertNotNull(result);
    }

    @Test
    void quandoReceberOsDadosDaCompraEParcelaForMaiorQueSeisRetornaUmaListaDeParcelas() {

        final ProductRequest productRequestToBuild = getProductRequest();

        final var paymentConditionRequestToBuild = PaymentConditionRequest.builder()
                .inputValue(0.0)
                .numberOfParcels(10)
                .build();

        final Request requestToBuild = getRequest(productRequestToBuild, paymentConditionRequestToBuild);

        final var result = listService.execute(requestToBuild);

        Assertions.assertNotNull(result);
    }

    private static Request getRequest(ProductRequest productRequestToBuild, PaymentConditionRequest paymentConditionRequestToBuild) {
        return Request.builder()
                .paymentConditionRequest(paymentConditionRequestToBuild)
                .productRequest(productRequestToBuild)
                .build();

    }

    private static ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .cod(222)
                .name("Taltal")
                .value(200.0)
                .build();
    }
}
