package com.pamela.desafiocompra.controller;

import com.pamela.desafiocompra.model.ListOfParcelsResponse;
import com.pamela.desafiocompra.model.PaymentConditionRequest;
import com.pamela.desafiocompra.model.ProductRequest;
import com.pamela.desafiocompra.model.Request;
import com.pamela.desafiocompra.service.ListOfParcelsServiceImpl;
import com.pamela.desafiocompra.utils.JsonConversionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ListOfParcelControllerTest {

    @Mock
    private ListOfParcelsServiceImpl service;

    @InjectMocks
    private ListOfParcelController controller;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    void quandoReceberRequisicaoRetornaListaDEParcelas() throws Exception {

        final var paymentConditionRequestToBuild = PaymentConditionRequest.builder()
                .inputValue(0.0)
                .numberOfParcels(10)
                .build();

        Mockito.when(service.execute(Mockito.any())).thenReturn(List.of(ListOfParcelsResponse.builder().build()));

        mockMvc.perform(get("/compra/alef")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonConversionUtils.asJsonString(getRequest(getProductRequest(), paymentConditionRequestToBuild))))
                        .andExpect(status().isOk());
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
