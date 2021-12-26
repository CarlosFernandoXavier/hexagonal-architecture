package com.unisinos.sistema.mapper;

import com.unisinos.sistema.entity.PagamentoEntity;
import com.unisinos.sistema.enumeration.FormaPagamentoEnum;
import com.unisinos.sistema.model.request.PagamentoRequest;
import com.unisinos.sistema.model.response.PagamentoResponse;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

public class PagamentoMapper {

    public static PagamentoEntity mapToEntity(PagamentoRequest pagamentoRequest, Integer id) {
        if (ObjectUtils.isEmpty(pagamentoRequest)) return null;

        return PagamentoEntity.builder()
                .id(id)
                .data(LocalDateTime.now())
                .formaPagamento(pagamentoRequest.getFormaPagamento().getCodigo())
                .itens(ItemMapper.mapToEntityList(pagamentoRequest.getItens()))
                .valorTotal(pagamentoRequest.getValorTotal())
                .build();
    }

    public static PagamentoResponse mapToResponse(PagamentoEntity pagamentoEntity) {
        if (ObjectUtils.isEmpty(pagamentoEntity)) return null;

        return PagamentoResponse.builder()
                .id(pagamentoEntity.getId())
                .data(pagamentoEntity.getData())
                .formaPagamento(FormaPagamentoEnum.getByCode(pagamentoEntity.getFormaPagamento()))
                .itens(ItemMapper.mapToResponseList(pagamentoEntity.getItens()))
                .valorTotal(pagamentoEntity.getValorTotal())
                .build();
    }
}
