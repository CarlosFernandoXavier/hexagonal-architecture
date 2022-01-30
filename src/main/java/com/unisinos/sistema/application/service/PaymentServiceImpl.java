package com.unisinos.sistema.application.service;

import com.unisinos.sistema.adapter.inbound.mapper.ItemMapper;
import com.unisinos.sistema.adapter.inbound.mapper.PagamentoMapper;
import com.unisinos.sistema.adapter.inbound.model.request.PagamentoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.PagamentoUpdateRequest;
import com.unisinos.sistema.application.domain.Pagamento;
import com.unisinos.sistema.adapter.inbound.validator.PagamentoValidator;
import com.unisinos.sistema.adapter.outbound.repository.PagamentoRepository;
import com.unisinos.sistema.adapter.outbound.entity.PagamentoEntity;
import com.unisinos.sistema.application.port.PaymentServicePort;
import com.unisinos.sistema.application.port.SequenceRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class PaymentServiceImpl implements PaymentServicePort {

    //TODO change the pagamentoRepository so that the implementation switches to the adapter layer
    private PagamentoRepository pagamentoRepository;
    private SequenceRepositoryPort sequenceRepositoryPortImpl;

    public PaymentServiceImpl(PagamentoRepository pagamentoRepository, SequenceRepositoryPort sequenceRepositoryPort) {
        this.pagamentoRepository = pagamentoRepository;
        this.sequenceRepositoryPortImpl = sequenceRepositoryPort;
    }

    public Pagamento payment(PagamentoRequest pagamentoRequest) {

        PagamentoValidator.validateItemValue(pagamentoRequest.getItens(), pagamentoRequest.getValorTotal());
        PagamentoValidator.validateCupomValue(pagamentoRequest.getValorCupom(), pagamentoRequest.getValorTotal());
        PagamentoEntity pagamentoEntity = PagamentoMapper
                .mapToEntity(pagamentoRequest, sequenceRepositoryPortImpl.getSequence("pagamento_sequence"));

        return PagamentoMapper.mapToResponse(pagamentoRepository.save(pagamentoEntity));
    }

    public void deletePayment(Integer id) {
        Optional.ofNullable(findPaymentById(id))
                .map(PagamentoEntity::getId)
                .ifPresent(pagamentoRepository::deleteById);
    }

    public PagamentoEntity findPaymentById(Integer id) {
        return Optional.ofNullable(pagamentoRepository.getById(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Pagamento com o id: %d, não foi encontrado", id)));
    }

    public Pagamento updatePayment(PagamentoUpdateRequest pagamento) {

        PagamentoValidator.validateItemValue(pagamento.getItens(), pagamento.getValorTotal());
        PagamentoValidator.validateCupomValue(pagamento.getValorCupom(), pagamento.getValorTotal());

        PagamentoEntity pagamentoEntity = findPaymentById(pagamento.getId());

        pagamentoEntity = updatePaymentFields(pagamentoEntity, pagamento);

        return PagamentoMapper.mapToResponse(pagamentoRepository.save(pagamentoEntity));
    }

    private PagamentoEntity updatePaymentFields(PagamentoEntity pagamentoEntity, PagamentoUpdateRequest pagamento) {
        pagamentoEntity.setData(pagamentoEntity.getData());
        pagamentoEntity.setFormaPagamento(pagamento.getFormaPagamento().getCodigo());
        pagamentoEntity.setItens(ItemMapper.mapToEntityList(pagamento.getItens()));
        pagamentoEntity.setValorTotal(pagamento.getValorTotal());
        return pagamentoEntity;
    }
}
