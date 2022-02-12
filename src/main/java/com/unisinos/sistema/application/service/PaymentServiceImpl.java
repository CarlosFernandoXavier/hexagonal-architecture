package com.unisinos.sistema.application.service;

import com.unisinos.sistema.adapter.inbound.mapper.ItemMapper;
import com.unisinos.sistema.adapter.inbound.mapper.PagamentoMapper;
import com.unisinos.sistema.adapter.inbound.model.request.PagamentoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.PagamentoUpdateRequest;
import com.unisinos.sistema.adapter.inbound.validator.PagamentoValidator;
import com.unisinos.sistema.adapter.outbound.entity.PaymentEntity;
import com.unisinos.sistema.application.domain.Pagamento;
import com.unisinos.sistema.application.port.PaymentRepositoryPort;
import com.unisinos.sistema.application.port.PaymentServicePort;
import com.unisinos.sistema.application.port.SequenceRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class PaymentServiceImpl implements PaymentServicePort {

    private PaymentRepositoryPort paymentRespositoryPort;
    private SequenceRepositoryPort sequenceRepositoryPort;

    public PaymentServiceImpl(PaymentRepositoryPort paymentRespositoryPort, SequenceRepositoryPort sequenceRepositoryPort) {
        this.paymentRespositoryPort = paymentRespositoryPort;
        this.sequenceRepositoryPort = sequenceRepositoryPort;
    }

    public Pagamento payment(PagamentoRequest pagamentoRequest) {

        PagamentoValidator.validateItemValue(pagamentoRequest.getItens(), pagamentoRequest.getValorTotal());
        PagamentoValidator.validateCupomValue(pagamentoRequest.getValorCupom(), pagamentoRequest.getValorTotal());
        PaymentEntity paymentEntity = PagamentoMapper
                .mapToEntity(pagamentoRequest, sequenceRepositoryPort.getSequence("pagamento_sequence"));

        return PagamentoMapper.mapToResponse(paymentRespositoryPort.save(paymentEntity));
    }

    public void deletePayment(Integer id) {
        Optional.ofNullable(findPaymentById(id))
                .map(PaymentEntity::getId)
                .ifPresent(paymentRespositoryPort::deleteById);
    }

    public PaymentEntity findPaymentById(Integer id) {
        return Optional.ofNullable(paymentRespositoryPort.getById(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Pagamento com o id: %d, não foi encontrado", id)));
    }

    public Pagamento updatePayment(PagamentoUpdateRequest pagamento) {

        PagamentoValidator.validateItemValue(pagamento.getItens(), pagamento.getValorTotal());
        PagamentoValidator.validateCupomValue(pagamento.getValorCupom(), pagamento.getValorTotal());

        PaymentEntity paymentEntity = findPaymentById(pagamento.getId());

        paymentEntity = updatePaymentFields(paymentEntity, pagamento);

        return PagamentoMapper.mapToResponse(paymentRespositoryPort.save(paymentEntity));
    }

    private PaymentEntity updatePaymentFields(PaymentEntity paymentEntity, PagamentoUpdateRequest pagamento) {
        paymentEntity.setData(paymentEntity.getData());
        paymentEntity.setFormaPagamento(pagamento.getFormaPagamento().getCodigo());
        paymentEntity.setItens(ItemMapper.mapToEntityList(pagamento.getItens()));
        paymentEntity.setValorTotal(pagamento.getValorTotal());
        return paymentEntity;
    }
}
