package com.unisinos.sistema.adapter.inbound.mapper;

import com.unisinos.sistema.adapter.inbound.model.request.PagamentoRequest;
import com.unisinos.sistema.adapter.outbound.entity.PaymentEntity;
import com.unisinos.sistema.adapter.outbound.builder.PaymentBuilder;
import com.unisinos.sistema.application.domain.Pagamento;
import com.unisinos.sistema.application.enumeration.FormaPagamentoEnum;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

public class PagamentoMapper {

    public static PaymentEntity mapToEntity(PagamentoRequest pagamentoRequest, Integer id) {
        if (ObjectUtils.isEmpty(pagamentoRequest)) return null;

        return PaymentEntity.builder()
                .id(id)
                .data(LocalDateTime.now())
                .formaPagamento(pagamentoRequest.getFormaPagamento().getCodigo())
                .itens(ItemMapper.mapToEntityList(pagamentoRequest.getItens()))
                .valorTotal(pagamentoRequest.getValorTotal())
                .build();
    }

    public static Pagamento mapToResponse(PaymentEntity paymentEntity) {
        if (ObjectUtils.isEmpty(paymentEntity)) return null;

        return PaymentBuilder.builder()
                .id(paymentEntity.getId())
                .data(paymentEntity.getData())
                .formaPagamento(FormaPagamentoEnum.getByCode(paymentEntity.getFormaPagamento()))
                .itens(ItemMapper.mapToResponseList(paymentEntity.getItens()))
                .valorTotal(paymentEntity.getValorTotal())
                .build();
    }
}
