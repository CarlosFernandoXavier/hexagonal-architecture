package com.unisinos.sistema.application.port;

import com.unisinos.sistema.adapter.inbound.model.request.ItensListaPrecoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.ListaPrecoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.RemoveItemRequest;
import com.unisinos.sistema.application.domain.ListaPreco;
import com.unisinos.sistema.adapter.outbound.entity.PriceListEntity;

import java.util.List;

public interface PriceListServicePort {
    ListaPreco addPriceList(ListaPrecoRequest listaPrecoRequest);

    List<ListaPreco> getPriceList(Integer idList);

    PriceListEntity findPriceListById(Integer idList);

    ListaPreco addItem(ItensListaPrecoRequest itemListaPreco);

    ListaPreco removeItem(RemoveItemRequest removeItemRequest);
}
