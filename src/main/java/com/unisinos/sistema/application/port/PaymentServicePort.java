package com.unisinos.sistema.application.port;

import com.unisinos.sistema.adapter.inbound.model.request.PagamentoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.PagamentoUpdateRequest;
import com.unisinos.sistema.application.domain.Pagamento;
import com.unisinos.sistema.adapter.outbound.entity.PagamentoEntity;

public interface PaymentServicePort {
    Pagamento payment(PagamentoRequest pagamentoRequest);

    void deletePayment(Integer id);

    PagamentoEntity findPaymentById(Integer id);

    Pagamento updatePayment(PagamentoUpdateRequest pagamento);
}
