package com.unisinos.sistema.validator;

import com.unisinos.sistema.entity.FilialEntity;
import com.unisinos.sistema.model.request.ItemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ItemListaPrecoValidator {

    public static void validateExistingItem(List<FilialEntity> filiais,
                                            List<ItemRequest> itens) {

        itens.forEach(itemRequest -> validateItemSubisiary(itemRequest.getCodigo(), filiais));
    }

    private static void validateItemSubisiary(String itemCode, List<FilialEntity> filiais) {
        filiais.forEach(filial -> {
            if (!isExistingItem(filial, itemCode)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Item com código: %s, não existe na filial %s",
                                itemCode, filial.getNome()));
            }
        });
    }

    private static Boolean isExistingItem(FilialEntity filial, String itemCode) {
        return filial.getItens().stream()
                .anyMatch(itemEstoqueEntity -> itemEstoqueEntity.getCodigo().equals(itemCode));
    }
}
