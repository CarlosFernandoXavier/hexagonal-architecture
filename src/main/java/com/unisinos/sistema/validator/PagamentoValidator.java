package com.unisinos.sistema.validator;

import com.unisinos.sistema.model.request.ItemRequest;
import com.unisinos.sistema.model.request.PagamentoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

public class PagamentoValidator {

    public static void validateItemValue(List<ItemRequest> itens, BigDecimal valorTotal) {
        BigDecimal valorItens = itens.stream()
                .map(ItemRequest::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (!valorItens.equals(valorTotal)) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Valor total difere do valor dos itens");
        }
    }

    public static void validateCupomValue(BigDecimal valorCumpom, BigDecimal valorTotal) {
        if (!ObjectUtils.isEmpty(valorCumpom) &&
                valorTotal.compareTo(valorCumpom) == 1) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Vale presente é menor que o valor da compra");
        }
    }


}
