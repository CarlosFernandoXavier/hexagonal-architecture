package com.unisinos.sistema.adapter.inbound.mapper;

import com.unisinos.sistema.adapter.inbound.model.FilialModel;
import com.unisinos.sistema.adapter.inbound.model.request.FilialRequest;
import com.unisinos.sistema.adapter.outbound.entity.SubsidiaryEntity;
import com.unisinos.sistema.adapter.outbound.builder.SubsidiaryBuilder;
import com.unisinos.sistema.application.domain.Filial;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FilialMapper {

    public static List<FilialModel> mapToModelList(List<SubsidiaryEntity> filialEntities) {
        if (ObjectUtils.isEmpty(filialEntities)) return List.of();

        return filialEntities.stream()
                .map(FilialMapper::mapToModel)
                .collect(Collectors.toList());
    }

    private static FilialModel mapToModel(SubsidiaryEntity subsidiary) {
        if (ObjectUtils.isEmpty(subsidiary)) return null;

        return FilialModel.builder()
                .id(subsidiary.getId())
                .nome(subsidiary.getNome())
                .itens(ItemEstoqueMapper.mapToModelList(subsidiary.getItens()))
                .build();
    }

    public static List<Filial> mapToResponseList(List<SubsidiaryEntity> subsidiary) {
        if (ObjectUtils.isEmpty(subsidiary)) return null;

        return subsidiary.stream()
                .map(FilialMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public static Filial mapToResponse(SubsidiaryEntity subsidiary) {
        if (ObjectUtils.isEmpty(subsidiary)) return null;
        return SubsidiaryBuilder.builder()
                .id(subsidiary.getId())
                .nome(subsidiary.getNome())
                .itens(ItemEstoqueMapper.mapToResponseList(subsidiary.getItens()))
                .build();
    }

    public static SubsidiaryEntity mapToEntity(FilialRequest filialRequest, Integer id) {
        if (ObjectUtils.isEmpty(filialRequest)) return null;

        return SubsidiaryEntity.builder()
                .id(id)
                .nome(filialRequest.getNome())
                .itens(ItemEstoqueMapper.mapToEntityList(filialRequest.getItens()))
                .build();
    }
}
