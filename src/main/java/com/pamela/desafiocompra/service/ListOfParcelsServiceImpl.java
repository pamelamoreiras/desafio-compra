package com.pamela.desafiocompra.service;

import com.pamela.desafiocompra.model.ListOfParcelsResponse;
import com.pamela.desafiocompra.model.Request;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListOfParcelsServiceImpl implements ListOfParcelService{

//    private Request request;

    public List<ListOfParcelsResponse> execute(final Request request) {

        ListOfParcelsResponse listResponse = ListOfParcelsResponse.builder().build();

        List<ListOfParcelsResponse> toAddResponseInList = new ArrayList<>();

        if (request.getPaymentConditionRequest().getNumberOfParcels() > 6) {

            double finalValuePerParcel = 0.0;

            for (int i = 7; i <= request.getPaymentConditionRequest().getNumberOfParcels(); i++) {

                ListOfParcelsResponse listaTeste = ListOfParcelsResponse.builder().build();

                listaTeste.setInterestRate(1.15/100);

                listaTeste.setNumberParcel(i);

                finalValuePerParcel = valuePerParcel(request) * Math.pow(1.0 + listaTeste.getInterestRate(), i);

                listaTeste.setValue(finalValuePerParcel);

                toAddResponseInList.add(listaTeste);
            }
        }

        if (request.getPaymentConditionRequest().getNumberOfParcels() <= 6){

            for (int i = 1; i <= request.getPaymentConditionRequest().getNumberOfParcels(); i++) {
                listResponse.setNumberParcel(i);
            }

            toAddResponseInList.stream()
                    .peek(item -> {
                        item.setNumberParcel(listResponse.getNumberParcel());
                        item.setValue(valuePerParcel(request));
                        item.setInterestRate(0.0);
                    })
                    .collect(Collectors.toList());
        }

        return toAddResponseInList;
    }

    private Double valuePerParcel(final Request request) {

        var productValue = request.getProductRequest().getValue();
        var initialValue = request.getPaymentConditionRequest().getInputValue();
        var parcels = request.getPaymentConditionRequest().getNumberOfParcels();

        return (productValue - initialValue) / parcels;
    }


}