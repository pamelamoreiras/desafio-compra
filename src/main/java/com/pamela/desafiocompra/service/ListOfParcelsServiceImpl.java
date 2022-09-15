package com.pamela.desafiocompra.service;

import com.pamela.desafiocompra.model.ListOfParcelsResponse;
import com.pamela.desafiocompra.model.Request;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListOfParcelsServiceImpl implements ListOfParcelService{

    public List<ListOfParcelsResponse> execute(final Request request) {

        ListOfParcelsResponse listResponse = ListOfParcelsResponse.builder().build();

        List<ListOfParcelsResponse> toAddResponseInList = new ArrayList<>();

        calculateParcelMoreThanSix(request, toAddResponseInList);

        calculateParcelLessThanSix(request, toAddResponseInList);

        return toAddResponseInList;
    }

    private void calculateParcelMoreThanSix(final Request request, final List<ListOfParcelsResponse> toAddResponseInList) {
        if (request.getPaymentConditionRequest().getNumberOfParcels() > 6) {

            double finalValuePerParcel = 0.0;

            for (int i = 7; i <= request.getPaymentConditionRequest().getNumberOfParcels(); i++) {

                ListOfParcelsResponse parcelsList = ListOfParcelsResponse.builder().build();

                parcelsList.setInterestRate(1.15/100);

                parcelsList.setNumberParcel(i);

                finalValuePerParcel = valuePerParcel(request) * Math.pow(1.0 + parcelsList.getInterestRate(), i);

                parcelsList.setValue(finalValuePerParcel);

                toAddResponseInList.add(parcelsList);
            }
        }
    }

    private void calculateParcelLessThanSix(final Request request, final List<ListOfParcelsResponse> toAddResponseInList) {
        if (request.getPaymentConditionRequest().getNumberOfParcels() <= 6){

            for (int i = 1; i <= request.getPaymentConditionRequest().getNumberOfParcels(); i++) {

                ListOfParcelsResponse parcelsList = ListOfParcelsResponse.builder().build();

                parcelsList.setNumberParcel(i);

                parcelsList.setValue(valuePerParcel(request));

                parcelsList.setInterestRate(0.0);

                toAddResponseInList.add(parcelsList);
            }

        }
    }

    private Double valuePerParcel(final Request request) {

        var productValue = request.getProductRequest().getValue();
        var initialValue = request.getPaymentConditionRequest().getInputValue();
        var parcels = request.getPaymentConditionRequest().getNumberOfParcels();

        return (productValue - initialValue) / parcels;
    }


}