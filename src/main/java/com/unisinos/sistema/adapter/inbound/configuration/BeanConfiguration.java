package com.unisinos.sistema.adapter.inbound.configuration;

import com.unisinos.sistema.adapter.outbound.repository.ListaPrecoRepository;
import com.unisinos.sistema.adapter.outbound.repository.PagamentoRepository;
import com.unisinos.sistema.application.port.FilialService;
import com.unisinos.sistema.application.port.SequenceRepositoryPort;
import com.unisinos.sistema.application.port.SubsidiaryRepositoryPort;
import com.unisinos.sistema.application.service.FilialServiceImpl;
import com.unisinos.sistema.application.service.ListaPrecoServiceImpl;
import com.unisinos.sistema.application.service.PagamentoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    FilialServiceImpl filialServiceImpl(SubsidiaryRepositoryPort filialRepository,
                                        SequenceRepositoryPort sequenceRepositoryPort) {
        return new FilialServiceImpl(filialRepository, sequenceRepositoryPort);
    }

    @Bean
    ListaPrecoServiceImpl listaPrecoServiceImpl(ListaPrecoRepository listaPrecoRepository,
                                                SequenceRepositoryPort sequenceRepositoryPort,
                                                FilialService filialService) {
        return new ListaPrecoServiceImpl(listaPrecoRepository, sequenceRepositoryPort, filialService);
    }

    @Bean
    PagamentoServiceImpl pagamentoServiceImpl(PagamentoRepository pagamentoRepository,
                                              SequenceRepositoryPort sequenceRepositoryPort) {
        return new PagamentoServiceImpl(pagamentoRepository, sequenceRepositoryPort);
    }
}
