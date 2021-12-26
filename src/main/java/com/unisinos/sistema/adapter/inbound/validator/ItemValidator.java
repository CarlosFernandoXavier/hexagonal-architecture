package com.unisinos.sistema.adapter.inbound.validator;

import com.unisinos.sistema.adapter.outbound.entity.ItemEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ItemValidator {
    public static Boolean isExistingItem(List<ItemEntity> itemEntities, String itemCode) {
        return itemEntities.stream()
                .anyMatch(itemEntity -> itemEntity.getCodigo().equals(itemCode));
    }

    public static void validateExistingItem(List<ItemEntity> itemEntities, String itemCode) {
        if(!isExistingItem(itemEntities, itemCode)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Item com código: %s, não existe na lista de preço", itemCode));
        }
    }
}
