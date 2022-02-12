package com.unisinos.sistema.application.service;

import com.unisinos.sistema.adapter.inbound.mapper.ItemMapper;
import com.unisinos.sistema.adapter.inbound.mapper.ListaPrecoMapper;
import com.unisinos.sistema.adapter.inbound.model.request.ItensListaPrecoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.ListaPrecoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.RemoveItemRequest;
import com.unisinos.sistema.adapter.inbound.validator.ItemListaPrecoValidator;
import com.unisinos.sistema.adapter.inbound.validator.ListaPrecoValidator;
import com.unisinos.sistema.adapter.outbound.entity.ItemEntity;
import com.unisinos.sistema.adapter.outbound.entity.PriceListEntity;
import com.unisinos.sistema.adapter.outbound.entity.SubsidiaryEntity;
import com.unisinos.sistema.application.domain.ListaPreco;
import com.unisinos.sistema.application.port.PriceListRepositoryPort;
import com.unisinos.sistema.application.port.PriceListServicePort;
import com.unisinos.sistema.application.port.SequenceRepositoryPort;
import com.unisinos.sistema.application.port.SubsidiaryServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.unisinos.sistema.adapter.inbound.validator.ItemValidator.isExistingItem;
import static com.unisinos.sistema.adapter.inbound.validator.ItemValidator.validateExistingItem;

public class PriceListServiceImpl implements PriceListServicePort {

    private PriceListRepositoryPort priceListPort;
    private SequenceRepositoryPort sequenceRepositoryPort;
    private SubsidiaryServicePort subsidiaryServicePort;

    public PriceListServiceImpl(PriceListRepositoryPort priceListPort, SequenceRepositoryPort sequenceRepositoryPort,
                                SubsidiaryServicePort subsidiaryServicePort) {
        this.priceListPort = priceListPort;
        this.sequenceRepositoryPort = sequenceRepositoryPort;
        this.subsidiaryServicePort = subsidiaryServicePort;
    }

    public ListaPreco addPriceList(ListaPrecoRequest listaPrecoRequest) {
        ListaPrecoValidator.validatePriceListDate(listaPrecoRequest);
        ListaPrecoValidator.validateSubsidiary(listaPrecoRequest);
        ListaPrecoValidator.validateItems(listaPrecoRequest);

        List<SubsidiaryEntity> listaFiliais = new ArrayList<>();
        listaPrecoRequest.getFiliais()
                .forEach(idFilial -> listaFiliais.add(subsidiaryServicePort.findSubsidiaryById(idFilial)));

        ItemListaPrecoValidator.validateExistingItem(listaFiliais, listaPrecoRequest.getItens());

        Integer sequence = sequenceRepositoryPort.getSequence("lista_preco_sequence");
        var listaPrecoEntity = ListaPrecoMapper.mapToEntity(listaPrecoRequest, sequence);

        return ListaPrecoMapper.mapToResponse(priceListPort.save(listaPrecoEntity));
    }

    public List<ListaPreco> getPriceList(Integer idList) {
        List<ListaPreco> lista = new ArrayList<>();
        if (Objects.isNull(idList)) {
            lista.addAll((ListaPrecoMapper.mapToResponseList(priceListPort.findAll())));
        } else {
            lista.add(ListaPrecoMapper.mapToResponse(findPriceListById(idList)));
        }
        return lista;
    }

    public PriceListEntity findPriceListById(Integer idList) {
        return Optional.ofNullable(priceListPort.getById(idList))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lista de preço com o id = %d, não existe", idList)));
    }

    public ListaPreco addItem(ItensListaPrecoRequest itemListaPreco) {
        PriceListEntity listaPreco = findPriceListById(itemListaPreco.getIdPriceList());

        List<SubsidiaryEntity> listaFiliais = new ArrayList<>();
        listaPreco.getFiliais()
                .forEach(idFilial -> listaFiliais.add(subsidiaryServicePort.findSubsidiaryById(idFilial)));

        ItemListaPrecoValidator.validateExistingItem(listaFiliais, itemListaPreco.getItens());

        itemListaPreco.getItens().forEach(itemRequest -> {
            validateEqualItem(listaPreco.getItens(), itemRequest.getCodigo(), listaPreco.getNome());
            listaPreco.getItens().add(ItemMapper.mapToEntity(itemRequest));
        });

        return ListaPrecoMapper.mapToResponse(priceListPort.save(listaPreco));
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

    public ListaPreco removeItem(RemoveItemRequest removeItemRequest) {
        PriceListEntity listaPreco = findPriceListById(removeItemRequest.getIdListaPreco());
        removeItemRequest.getIdItens().forEach(idItem -> validateExistingItem(listaPreco.getItens(), idItem));

        listaPreco.setItens(listaPreco.getItens().stream()
                .filter(itemEntity -> !isPresentItem(itemEntity, removeItemRequest.getIdItens()))
                .collect(Collectors.toList()));

        return ListaPrecoMapper.mapToResponse(priceListPort.save(listaPreco));
    }

    private Boolean isPresentItem(ItemEntity itemEntity, List<String> idItens) {
        return idItens.stream().anyMatch(id -> id.equals(itemEntity.getCodigo()));
    }
}
