package com.unisinos.sistema.mapper;

import com.unisinos.sistema.entity.ItemEstoqueEntity;
import com.unisinos.sistema.model.ItemEstoqueModel;
import com.unisinos.sistema.model.request.ItemEstoqueRequest;
import com.unisinos.sistema.model.response.ItemEstoqueResponse;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ItemEstoqueMapper {

    public static List<ItemEstoqueModel> mapToModelList(List<ItemEstoqueEntity> itemEntities) {
        if (ObjectUtils.isEmpty(itemEntities)) return Collections.emptyList();

        return itemEntities.stream()
                .map(ItemEstoqueMapper::mapToModel)
                .collect(Collectors.toList());
    }

    private static ItemEstoqueModel mapToModel(ItemEstoqueEntity itemEstoqueEntity) {
        if (ObjectUtils.isEmpty(itemEstoqueEntity)) return null;

        return ItemEstoqueModel.builder()
                .codigo(itemEstoqueEntity.getCodigo())
                .nome(itemEstoqueEntity.getNome())
                .quantidade(itemEstoqueEntity.getQuantidade())
                .fornecedor(itemEstoqueEntity.getFornecedor())
                .build();
    }

    public static List<ItemEstoqueResponse> mapToResponseList(List<ItemEstoqueEntity> itemEntities) {
        if (ObjectUtils.isEmpty(itemEntities)) return Collections.emptyList();

        return itemEntities.stream()
                .map(ItemEstoqueMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    private static ItemEstoqueResponse mapToResponse(ItemEstoqueEntity itemEstoqueEntity) {
        if (ObjectUtils.isEmpty(itemEstoqueEntity)) return null;

        return ItemEstoqueResponse.builder()
                .codigo(itemEstoqueEntity.getCodigo())
                .nome(itemEstoqueEntity.getNome())
                .quantidade(itemEstoqueEntity.getQuantidade())
                .fornecedor(itemEstoqueEntity.getFornecedor())
                .build();
    }

    public static List<ItemEstoqueEntity> mapToEntityList(List<ItemEstoqueRequest> itemEstoqueRequests) {
        if (ObjectUtils.isEmpty(itemEstoqueRequests)) return Collections.emptyList();

        return itemEstoqueRequests.stream()
                .map(ItemEstoqueMapper::mapToEntity)
                .collect(Collectors.toList());
    }

    public static ItemEstoqueEntity mapToEntity(ItemEstoqueRequest itemEstoqueRequest) {
        if (ObjectUtils.isEmpty(itemEstoqueRequest)) return null;

        return ItemEstoqueEntity.builder()
                .nome(itemEstoqueRequest.getNome())
                .quantidade(itemEstoqueRequest.getQuantidade())
                .codigo(itemEstoqueRequest.getCodigo())
                .fornecedor(itemEstoqueRequest.getFornecedor())
                .build();
    }
}
