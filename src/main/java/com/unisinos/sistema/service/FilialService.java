package com.unisinos.sistema.service;

import com.unisinos.sistema.entity.FilialEntity;
import com.unisinos.sistema.mapper.FilialMapper;
import com.unisinos.sistema.mapper.ItemEstoqueMapper;
import com.unisinos.sistema.model.request.FilialRequest;
import com.unisinos.sistema.model.request.SubsidiaryItemRequest;
import com.unisinos.sistema.model.response.FilialResponse;
import com.unisinos.sistema.repository.FilialRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FilialService {

    private FilialRepository filialRepository;
    private SequenceService sequenceService;

    public List<FilialEntity> findAllSubsidiaries() {
        return filialRepository.findAll();
    }

    public List<FilialResponse> getSubsidiary(Integer id) {
        if (Objects.isNull(id)) {
            return FilialMapper.mapToResponseList(findAllSubsidiaries());
        } else {
            List<FilialResponse> filialResponses = new ArrayList<>();
            filialResponses.add(FilialMapper.mapToResponse(findSubsidiaryById(id)));
            return filialResponses;
        }
    }

    public FilialEntity findSubsidiaryById(Integer id) {
        return Optional.ofNullable(filialRepository.getById(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Filial com o id: %d, não foi encontrada", id)));
    }

    public FilialResponse createSubsidiary(FilialRequest filialRequest) {
        return FilialMapper.mapToResponse(filialRepository.save(FilialMapper
                .mapToEntity(filialRequest, sequenceService.getSequence("filial_sequence"))));
    }

    public FilialResponse addItem(SubsidiaryItemRequest subsidiaryItemRequest) {
        FilialEntity filialEntity = filialRepository.getById(subsidiaryItemRequest.getSubsidiaryId());
        subsidiaryItemRequest.getItens().forEach(item -> {
            filialEntity.getItens().add(ItemEstoqueMapper.mapToEntity(item));
        });
        return FilialMapper.mapToResponse(filialRepository.save(filialEntity));
    }
}
