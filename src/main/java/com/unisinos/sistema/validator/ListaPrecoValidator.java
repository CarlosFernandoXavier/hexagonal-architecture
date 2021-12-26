package com.unisinos.sistema.validator;

import com.unisinos.sistema.model.request.ListaPrecoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

public class ListaPrecoValidator {

    public static void validatePriceListDate(ListaPrecoRequest listaPrecoRequest) {
        if (listaPrecoRequest.getDataInicial().getYear() > listaPrecoRequest.getDataFinal().getYear()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "O ano da dataInicial é maior que o da dataFinal");

        } else if (listaPrecoRequest.getDataInicial().getMonthValue() > listaPrecoRequest.getDataFinal().getMonthValue()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Mês da dataInicial é maior que a dataFinal");

        } else if (listaPrecoRequest.getDataInicial().getMonthValue() == listaPrecoRequest.getDataFinal().getMonthValue() &&
                listaPrecoRequest.getDataInicial().getDayOfMonth() > listaPrecoRequest.getDataFinal().getDayOfMonth()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Dia da dataInicial é maior que a dataFinal");
        }
    }

    public static void validateSubsidiary(ListaPrecoRequest listaPrecoRequest) {
        if (ObjectUtils.isEmpty(listaPrecoRequest.getFiliais()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O campo filiais está vazio");
    }

    public static void validateItems(ListaPrecoRequest listaPrecoRequest) {
        if (ObjectUtils.isEmpty(listaPrecoRequest.getItens())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "O campo itens está vazio");
    }
}
