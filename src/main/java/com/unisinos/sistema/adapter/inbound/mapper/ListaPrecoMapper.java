package com.unisinos.sistema.adapter.inbound.mapper;

import com.unisinos.sistema.adapter.inbound.model.request.ListaPrecoRequest;
import com.unisinos.sistema.adapter.outbound.builder.PriceListBuilder;
import com.unisinos.sistema.application.domain.ListaPreco;
import com.unisinos.sistema.adapter.outbound.entity.PriceListEntity;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ListaPrecoMapper {

    public static PriceListEntity mapToEntity(ListaPrecoRequest listaPrecoRequest, Integer sequence) {
        if (ObjectUtils.isEmpty(listaPrecoRequest)) return null;

        return PriceListEntity.builder()
                .id(sequence)
                .nome(listaPrecoRequest.getNome())
                .dataInicial(listaPrecoRequest.getDataInicial())
                .dataFinal(listaPrecoRequest.getDataFinal())
                .itens(ItemMapper.mapToEntityList(listaPrecoRequest.getItens()))
                .filiais(listaPrecoRequest.getFiliais())
                .build();
    }

    public static ListaPreco mapToResponse(PriceListEntity priceListEntity) {
        if (ObjectUtils.isEmpty(priceListEntity)) return null;

        return PriceListBuilder.builder()
                .id(priceListEntity.getId())
                .nome(priceListEntity.getNome())
                .dataInicial(priceListEntity.getDataInicial())
                .dataFinal(priceListEntity.getDataFinal())
                .filiais(priceListEntity.getFiliais())
                .itens(ItemMapper.mapToResponseList(priceListEntity.getItens()))
                .build();
    }
    public static List<ListaPreco> mapToResponseList(List<PriceListEntity> listaPreco) {
        if (ObjectUtils.isEmpty(listaPreco)) return List.of();

        return listaPreco.stream()
                .map(ListaPrecoMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}
