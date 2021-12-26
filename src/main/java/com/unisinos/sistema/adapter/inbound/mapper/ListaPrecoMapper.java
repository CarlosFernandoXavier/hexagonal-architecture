package com.unisinos.sistema.adapter.inbound.mapper;

import com.unisinos.sistema.adapter.inbound.model.request.ListaPrecoRequest;
import com.unisinos.sistema.application.domain.ListaPreco;
import com.unisinos.sistema.adapter.outbound.entity.ListaPrecoEntity;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ListaPrecoMapper {

    public static ListaPrecoEntity mapToEntity(ListaPrecoRequest listaPrecoRequest, Integer sequence) {
        if (ObjectUtils.isEmpty(listaPrecoRequest)) return null;

        return ListaPrecoEntity.builder()
                .id(sequence)
                .nome(listaPrecoRequest.getNome())
                .dataInicial(listaPrecoRequest.getDataInicial())
                .dataFinal(listaPrecoRequest.getDataFinal())
                .itens(ItemMapper.mapToEntityList(listaPrecoRequest.getItens()))
                .filiais(listaPrecoRequest.getFiliais())
                .build();
    }

    public static ListaPreco mapToResponse(ListaPrecoEntity listaPrecoEntity) {
        if (ObjectUtils.isEmpty(listaPrecoEntity)) return null;

        return ListaPreco.builder()
                .id(listaPrecoEntity.getId())
                .nome(listaPrecoEntity.getNome())
                .dataInicial(listaPrecoEntity.getDataInicial())
                .dataFinal(listaPrecoEntity.getDataFinal())
                .filiais(listaPrecoEntity.getFiliais())
                .itens(ItemMapper.mapToResponseList(listaPrecoEntity.getItens()))
                .build();
    }
    public static List<ListaPreco> mapToResponseList(List<ListaPrecoEntity> listaPreco) {
        if (ObjectUtils.isEmpty(listaPreco)) return List.of();

        return listaPreco.stream()
                .map(ListaPrecoMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}
