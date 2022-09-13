package com.pamela.desafiocompra.service;

import com.pamela.desafiocompra.model.ListOfParcelsResponse;
import com.pamela.desafiocompra.model.Request;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListOfParcelsServiceImpl implements ListOfParcelService{

//    private Request request;

    public List<ListOfParcelsResponse> execute(final Request request) {

        ListOfParcelsResponse listResponse = ListOfParcelsResponse.builder().build();

        var toAddResponseInList = List.of(listResponse);

        if (request.getPaymentConditionRequest().getNumberOfParcels() > 6) {

            listResponse.setInterestRate(1.15/100);

            double finalValuePerParcel = 0.0;

            for (int i = 7; i <= request.getPaymentConditionRequest().getNumberOfParcels(); i++) {

                listResponse.setNumberParcel(i);
                finalValuePerParcel = valuePerParcel(request) * Math.pow(1.0 + listResponse.getInterestRate(), i);
            }

            double finalValuePerParcel1 = finalValuePerParcel;

            toAddResponseInList.stream()
                    .peek(item -> {
                        item.setNumberParcel(listResponse.getNumberParcel());
                        item.setValue(finalValuePerParcel1);
                        item.setInterestRate(listResponse.getInterestRate());
                    })
                    .collect(Collectors.toList());
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