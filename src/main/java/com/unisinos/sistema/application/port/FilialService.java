package com.unisinos.sistema.application.port;

import com.unisinos.sistema.adapter.inbound.model.request.FilialRequest;
import com.unisinos.sistema.adapter.inbound.model.request.SubsidiaryItemRequest;
import com.unisinos.sistema.application.domain.Filial;
import com.unisinos.sistema.adapter.outbound.entity.SubsidiaryEntity;

import java.util.List;

public interface FilialService {
    List<SubsidiaryEntity> findAllSubsidiaries();

    List<Filial> getSubsidiary(Integer id);

    SubsidiaryEntity findSubsidiaryById(Integer id);

    Filial createSubsidiary(FilialRequest filialRequest);

    Filial addItem(SubsidiaryItemRequest subsidiaryItemRequest);
}
