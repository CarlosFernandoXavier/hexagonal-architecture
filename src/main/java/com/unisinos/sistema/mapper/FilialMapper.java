package com.unisinos.sistema.mapper;

import com.unisinos.sistema.entity.FilialEntity;
import com.unisinos.sistema.model.FilialModel;
import com.unisinos.sistema.model.request.FilialRequest;
import com.unisinos.sistema.model.response.FilialResponse;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FilialMapper {

    public static List<FilialModel> mapToModelList(List<FilialEntity> filialEntities) {
        if (ObjectUtils.isEmpty(filialEntities)) return List.of();

        return filialEntities.stream()
                .map(FilialMapper::mapToModel)
                .collect(Collectors.toList());
    }

    private static FilialModel mapToModel(FilialEntity filialEntity) {
        if (ObjectUtils.isEmpty(filialEntity)) return null;

        return FilialModel.builder()
                .id(filialEntity.getId())
                .nome(filialEntity.getNome())
                .itens(ItemEstoqueMapper.mapToModelList(filialEntity.getItens()))
                .build();
    }

    public static List<FilialResponse> mapToResponseList(List<FilialEntity> filialEntity) {
        if (ObjectUtils.isEmpty(filialEntity)) return null;

        return filialEntity.stream()
                .map(FilialMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public static FilialResponse mapToResponse(FilialEntity filialEntity) {
        if (ObjectUtils.isEmpty(filialEntity)) return null;

        return FilialResponse.builder()
                .id(filialEntity.getId())
                .nome(filialEntity.getNome())
                .itens(ItemEstoqueMapper.mapToResponseList(filialEntity.getItens()))
                .build();
    }

    public static FilialEntity mapToEntity(FilialRequest filialRequest, Integer id) {
        if (ObjectUtils.isEmpty(filialRequest)) return null;

        return FilialEntity.builder()
                .id(id)
                .nome(filialRequest.getNome())
                .itens(ItemEstoqueMapper.mapToEntityList(filialRequest.getItens()))
                .build();
    }
}
