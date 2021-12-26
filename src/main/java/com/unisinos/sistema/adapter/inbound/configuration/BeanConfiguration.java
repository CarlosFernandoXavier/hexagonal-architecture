package com.unisinos.sistema.adapter.inbound.configuration;

import com.unisinos.sistema.adapter.outbound.repository.FilialRepository;
import com.unisinos.sistema.adapter.outbound.repository.ListaPrecoRepository;
import com.unisinos.sistema.adapter.outbound.repository.PagamentoRepository;
import com.unisinos.sistema.application.port.FilialService;
import com.unisinos.sistema.application.port.SequenceService;
import com.unisinos.sistema.application.service.FilialServiceImpl;
import com.unisinos.sistema.application.service.ListaPrecoServiceImpl;
import com.unisinos.sistema.application.service.PagamentoServiceImpl;
import com.unisinos.sistema.application.service.SequenceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class BeanConfiguration {
    @Bean
    FilialServiceImpl filialServiceImpl(FilialRepository filialRepository, SequenceService sequenceService) {
        return new FilialServiceImpl(filialRepository, sequenceService);
    }

    @Bean
    ListaPrecoServiceImpl listaPrecoServiceImpl(ListaPrecoRepository listaPrecoRepository,
                                                SequenceService sequenceService,
                                                FilialService filialService) {
        return new ListaPrecoServiceImpl(listaPrecoRepository, sequenceService, filialService);
    }

    @Bean
    PagamentoServiceImpl pagamentoServiceImpl(PagamentoRepository pagamentoRepository,
                                              SequenceService sequenceService) {
        return new PagamentoServiceImpl(pagamentoRepository, sequenceService);
    }

    @Bean
    SequenceServiceImpl sequenceServiceImpl(MongoOperations mongoOperations) {
        return new SequenceServiceImpl(mongoOperations);
    }
}
