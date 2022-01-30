package com.unisinos.sistema.adapter.configuration;

import com.unisinos.sistema.adapter.outbound.repository.ListaPrecoRepository;
import com.unisinos.sistema.adapter.outbound.repository.PagamentoRepository;
import com.unisinos.sistema.application.port.SubsidiaryServicePort;
import com.unisinos.sistema.application.port.SequenceRepositoryPort;
import com.unisinos.sistema.application.port.SubsidiaryRepositoryPort;
import com.unisinos.sistema.application.service.SubsidiaryServiceImpl;
import com.unisinos.sistema.application.service.PriceListServiceImpl;
import com.unisinos.sistema.application.service.PaymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    SubsidiaryServiceImpl filialServiceImpl(SubsidiaryRepositoryPort filialRepository,
                                            SequenceRepositoryPort sequenceRepositoryPort) {
        return new SubsidiaryServiceImpl(filialRepository, sequenceRepositoryPort);
    }

    @Bean
    PriceListServiceImpl listaPrecoServiceImpl(ListaPrecoRepository listaPrecoRepository,
                                               SequenceRepositoryPort sequenceRepositoryPort,
                                               SubsidiaryServicePort subsidiaryServicePort) {
        return new PriceListServiceImpl(listaPrecoRepository, sequenceRepositoryPort, subsidiaryServicePort);
    }

    @Bean
    PaymentServiceImpl pagamentoServiceImpl(PagamentoRepository pagamentoRepository,
                                            SequenceRepositoryPort sequenceRepositoryPort) {
        return new PaymentServiceImpl(pagamentoRepository, sequenceRepositoryPort);
    }
}
