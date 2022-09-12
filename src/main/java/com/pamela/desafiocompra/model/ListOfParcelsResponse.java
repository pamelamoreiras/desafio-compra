package com.pamela.desafiocompra.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListOfParcelsResponse {

    private Integer numberParcel;
    private Double value;
    private Double interestRate;
}
