package com.unisinos.sistema.service;

import com.unisinos.sistema.entity.FilialEntity;
import com.unisinos.sistema.entity.ItemEntity;
import com.unisinos.sistema.entity.ListaPrecoEntity;
import com.unisinos.sistema.mapper.ItemMapper;
import com.unisinos.sistema.mapper.ListaPrecoMapper;
import com.unisinos.sistema.model.request.ItensListaPrecoRequest;
import com.unisinos.sistema.model.request.ListaPrecoRequest;
import com.unisinos.sistema.model.request.RemoveItemRequest;
import com.unisinos.sistema.model.response.ListaPrecoResponse;
import com.unisinos.sistema.repository.ListaPrecoRepository;
import com.unisinos.sistema.validator.ItemListaPrecoValidator;
import com.unisinos.sistema.validator.ListaPrecoValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.unisinos.sistema.validator.ItemValidator.isExistingItem;
import static com.unisinos.sistema.validator.ItemValidator.validateExistingItem;

@Service
@AllArgsConstructor
public class ListaPrecoService {

    private ListaPrecoRepository listaPrecoRepository;
    private SequenceService sequenceService;
    private FilialService filialService;

    public ListaPrecoResponse addPriceList(ListaPrecoRequest listaPrecoRequest) {
        ListaPrecoValidator.validatePriceListDate(listaPrecoRequest);
        ListaPrecoValidator.validateSubsidiary(listaPrecoRequest);
        ListaPrecoValidator.validateItems(listaPrecoRequest);

        List<FilialEntity> listaFiliais = new ArrayList<>();
        listaPrecoRequest.getFiliais()
                .forEach(idFilial -> listaFiliais.add(filialService.findSubsidiaryById(idFilial)));

        ItemListaPrecoValidator.validateExistingItem(listaFiliais, listaPrecoRequest.getItens());

        Integer sequence = sequenceService.getSequence("lista_preco_sequence");
        var listaPrecoEntity = ListaPrecoMapper.mapToEntity(listaPrecoRequest, sequence);

        return ListaPrecoMapper.mapToResponse(listaPrecoRepository.save(listaPrecoEntity));
    }

    public List<ListaPrecoResponse> getPriceList(Integer idList) {
        List<ListaPrecoResponse> lista = new ArrayList<>();
        if (Objects.isNull(idList)) {
            lista.addAll((ListaPrecoMapper.mapToResponseList(listaPrecoRepository.findAll())));
        } else {
            lista.add(ListaPrecoMapper.mapToResponse(findPriceListById(idList)));
        }
        return lista;
    }

    public ListaPrecoEntity findPriceListById(Integer idList) {
        return Optional.ofNullable(listaPrecoRepository.getById(idList))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lista de preço com o id = %d, não existe", idList)));
    }

    public ListaPrecoResponse addItem(ItensListaPrecoRequest itemListaPreco) {
        ListaPrecoEntity listaPreco = findPriceListById(itemListaPreco.getIdPriceList());

        List<FilialEntity> listaFiliais = new ArrayList<>();
        listaPreco.getFiliais()
                .forEach(idFilial -> listaFiliais.add(filialService.findSubsidiaryById(idFilial)));

        ItemListaPrecoValidator.validateExistingItem(listaFiliais, itemListaPreco.getItens());

        itemListaPreco.getItens().forEach(itemRequest -> {
            validateEqualItem(listaPreco.getItens(), itemRequest.getCodigo(), listaPreco.getNome());
            listaPreco.getItens().add(ItemMapper.mapToEntity(itemRequest));
        });

        return ListaPrecoMapper.mapToResponse(listaPrecoRepository.save(listaPreco));
    }

    private void validateEqualItem(List<ItemEntity> itensListaPreco,
                                   String codigoNovoItem,
                                   String nomeListaPreco) {

        if (isExistingItem(itensListaPreco, codigoNovoItem)) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    String.format("Item com o código %s, já existe na lista %s",
                            codigoNovoItem,
                            nomeListaPreco));
        }
    }

    public ListaPrecoResponse removeItem(RemoveItemRequest removeItemRequest) {
        ListaPrecoEntity listaPreco = findPriceListById(removeItemRequest.getIdListaPreco());
        removeItemRequest.getIdItens().forEach(idItem -> validateExistingItem(listaPreco.getItens(), idItem));

        listaPreco.setItens(listaPreco.getItens().stream()
                .filter(itemEntity -> !isPresentItem(itemEntity, removeItemRequest.getIdItens()))
                .collect(Collectors.toList()));

        return ListaPrecoMapper.mapToResponse(listaPrecoRepository.save(listaPreco));
    }

    private Boolean isPresentItem(ItemEntity itemEntity, List<String> idItens) {
        return idItens.stream().anyMatch(id -> id.equals(itemEntity.getCodigo()));
    }
}
