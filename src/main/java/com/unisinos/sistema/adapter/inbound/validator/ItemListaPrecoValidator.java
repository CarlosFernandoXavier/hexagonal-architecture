package com.unisinos.sistema.adapter.inbound.validator;

import com.unisinos.sistema.adapter.inbound.model.request.ItemRequest;
import com.unisinos.sistema.adapter.outbound.entity.SubsidiaryEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ItemListaPrecoValidator {

    public static void validateExistingItem(List<SubsidiaryEntity> filiais,
                                            List<ItemRequest> itens) {

        itens.forEach(itemRequest -> validateItemSubisiary(itemRequest.getCodigo(), filiais));
    }

    private static void validateItemSubisiary(String itemCode, List<SubsidiaryEntity> filiais) {
        filiais.forEach(filial -> {
            if (!isExistingItem(filial, itemCode)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Item com código: %s, não existe na filial %s",
                                itemCode, filial.getNome()));
            }
        });
    }

    private static Boolean isExistingItem(SubsidiaryEntity filial, String itemCode) {
        return filial.getItens().stream()
                .anyMatch(itemEstoqueEntity -> itemEstoqueEntity.getCodigo().equals(itemCode));
    }
}
