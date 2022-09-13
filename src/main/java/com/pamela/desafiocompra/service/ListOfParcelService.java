package com.pamela.desafiocompra.service;

import com.pamela.desafiocompra.model.ListOfParcelsResponse;
import com.pamela.desafiocompra.model.Request;

import java.util.List;

public interface ListOfParcelService {

    List<ListOfParcelsResponse> execute(final Request request);
}
