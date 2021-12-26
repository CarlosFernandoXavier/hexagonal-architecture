package com.unisinos.sistema.application.port;

import com.unisinos.sistema.adapter.inbound.model.request.ItensListaPrecoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.ListaPrecoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.RemoveItemRequest;
import com.unisinos.sistema.application.domain.ListaPreco;
import com.unisinos.sistema.adapter.outbound.entity.ListaPrecoEntity;

import java.util.List;

public interface ListaPrecoService {
    ListaPreco addPriceList(ListaPrecoRequest listaPrecoRequest);

    List<ListaPreco> getPriceList(Integer idList);

    ListaPrecoEntity findPriceListById(Integer idList);

    ListaPreco addItem(ItensListaPrecoRequest itemListaPreco);

    ListaPreco removeItem(RemoveItemRequest removeItemRequest);
}