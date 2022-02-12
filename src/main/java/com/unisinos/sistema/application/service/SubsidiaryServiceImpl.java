package com.unisinos.sistema.application.service;

import com.unisinos.sistema.adapter.inbound.mapper.FilialMapper;
import com.unisinos.sistema.adapter.inbound.mapper.ItemEstoqueMapper;
import com.unisinos.sistema.adapter.inbound.model.request.FilialRequest;
import com.unisinos.sistema.adapter.inbound.model.request.SubsidiaryItemRequest;
import com.unisinos.sistema.adapter.outbound.entity.SubsidiaryEntity;
import com.unisinos.sistema.application.domain.Filial;
import com.unisinos.sistema.application.port.SubsidiaryServicePort;
import com.unisinos.sistema.application.port.SequenceRepositoryPort;
import com.unisinos.sistema.application.port.SubsidiaryRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SubsidiaryServiceImpl implements SubsidiaryServicePort {

    private SubsidiaryRepositoryPort filialRepositoryPort;
    private SequenceRepositoryPort sequenceRepositoryPort;

    public SubsidiaryServiceImpl(SubsidiaryRepositoryPort filialRepositoryPort, SequenceRepositoryPort sequenceRepositoryPort) {
        this.filialRepositoryPort = filialRepositoryPort;
        this.sequenceRepositoryPort = sequenceRepositoryPort;
    }

    public List<SubsidiaryEntity> findAllSubsidiaries() {
        return filialRepositoryPort.findAll();
    }

    public List<Filial> getSubsidiary(Integer id) {
        if (Objects.isNull(id)) {
            return FilialMapper.mapToResponseList(findAllSubsidiaries());
        } else {
            List<Filial> filialRespons = new ArrayList<>();
            filialRespons.add(FilialMapper.mapToResponse(findSubsidiaryById(id)));
            return filialRespons;
        }
    }

    public SubsidiaryEntity findSubsidiaryById(Integer id) {
        return Optional.ofNullable(filialRepositoryPort.getById(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Filial com o id: %d, não foi encontrada", id)));
    }

    public Filial createSubsidiary(FilialRequest filialRequest) {
        return FilialMapper.mapToResponse(filialRepositoryPort.save(FilialMapper
                .mapToEntity(filialRequest, sequenceRepositoryPort.getSequence("filial_sequence"))));
    }

    public Filial addItem(SubsidiaryItemRequest subsidiaryItemRequest) {
        SubsidiaryEntity subsidiary = filialRepositoryPort.getById(subsidiaryItemRequest.getSubsidiaryId());
        subsidiaryItemRequest.getItens().forEach(item -> {
            subsidiary.getItens().add(ItemEstoqueMapper.mapToEntity(item));
        });
        return FilialMapper.mapToResponse(filialRepositoryPort.save(subsidiary));
    }
}
