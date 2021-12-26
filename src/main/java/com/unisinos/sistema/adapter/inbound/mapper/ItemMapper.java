package com.unisinos.sistema.adapter.inbound.mapper;

import com.unisinos.sistema.adapter.inbound.model.request.ItemRequest;
import com.unisinos.sistema.application.domain.Item;
import com.unisinos.sistema.adapter.outbound.entity.ItemEntity;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {

    public static List<ItemEntity> mapToEntityList(List<ItemRequest> itemRequest) {
        if (ObjectUtils.isEmpty(itemRequest)) return Collections.emptyList();

        return itemRequest.stream()
                .map(ItemMapper::mapToEntity)
                .collect(Collectors.toList());
    }

    public static ItemEntity mapToEntity(ItemRequest itemRequest) {
        if (ObjectUtils.isEmpty(itemRequest)) return null;

        return ItemEntity.builder()
                .codigo(itemRequest.getCodigo())
                .nome(itemRequest.getNome())
                .preco(itemRequest.getPreco())
                .build();
    }

    public static List<Item> mapToResponseList(List<ItemEntity> itemEntities) {
        if (ObjectUtils.isEmpty(itemEntities)) return Collections.emptyList();

        return itemEntities.stream()
                .map(ItemMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    private static Item mapToResponse(ItemEntity itemEntity) {
        if (ObjectUtils.isEmpty(itemEntity)) return null;

        return Item.builder()
                .codigo(itemEntity.getCodigo())
                .nome(itemEntity.getNome())
                .preco(itemEntity.getPreco())
                .build();
    }
}
