package com.unisinos.sistema.service;

import com.unisinos.sistema.entity.PagamentoEntity;
import com.unisinos.sistema.mapper.ItemMapper;
import com.unisinos.sistema.mapper.PagamentoMapper;
import com.unisinos.sistema.model.request.PagamentoRequest;
import com.unisinos.sistema.model.request.PagamentoUpdateRequest;
import com.unisinos.sistema.model.response.PagamentoResponse;
import com.unisinos.sistema.repository.PagamentoRepository;
import com.unisinos.sistema.validator.PagamentoValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PagamentoService {

    private PagamentoRepository pagamentoRepository;
    private SequenceService sequenceService;

    public PagamentoResponse payment(PagamentoRequest pagamentoRequest) {

        PagamentoValidator.validateItemValue(pagamentoRequest.getItens(), pagamentoRequest.getValorTotal());
        PagamentoValidator.validateCupomValue(pagamentoRequest.getValorCupom(), pagamentoRequest.getValorTotal());
        PagamentoEntity pagamentoEntity = PagamentoMapper
                .mapToEntity(pagamentoRequest, sequenceService.getSequence("pagamento_sequence"));

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

    public PagamentoResponse updatePayment(PagamentoUpdateRequest pagamento) {

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
