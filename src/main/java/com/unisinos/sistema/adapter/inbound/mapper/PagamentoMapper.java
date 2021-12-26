package com.unisinos.sistema.adapter.inbound.mapper;

import com.unisinos.sistema.application.enumeration.FormaPagamentoEnum;
import com.unisinos.sistema.adapter.inbound.model.request.PagamentoRequest;
import com.unisinos.sistema.application.domain.Pagamento;
import com.unisinos.sistema.adapter.outbound.entity.PagamentoEntity;
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

    public static Pagamento mapToResponse(PagamentoEntity pagamentoEntity) {
        if (ObjectUtils.isEmpty(pagamentoEntity)) return null;

        return Pagamento.builder()
                .id(pagamentoEntity.getId())
                .data(pagamentoEntity.getData())
                .formaPagamento(FormaPagamentoEnum.getByCode(pagamentoEntity.getFormaPagamento()))
                .itens(ItemMapper.mapToResponseList(pagamentoEntity.getItens()))
                .valorTotal(pagamentoEntity.getValorTotal())
                .build();
    }
}
