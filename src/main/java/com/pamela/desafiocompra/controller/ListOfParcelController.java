package com.pamela.desafiocompra.controller;

import com.pamela.desafiocompra.model.ListOfParcelsResponse;
import com.pamela.desafiocompra.model.Request;
import com.pamela.desafiocompra.service.ListOfParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/compra")
public class ListOfParcelController {

    private final ListOfParcelService service;

    @GetMapping(value = "/alef")
    public List<ListOfParcelsResponse> executar(@RequestBody final Request request) {
        return service.execute(request);
    }
}
