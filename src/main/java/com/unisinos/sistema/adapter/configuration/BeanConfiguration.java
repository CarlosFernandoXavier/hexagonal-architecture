package com.unisinos.sistema.adapter.configuration;

import com.unisinos.sistema.application.port.*;
import com.unisinos.sistema.application.service.PaymentServiceImpl;
import com.unisinos.sistema.application.service.PriceListServiceImpl;
import com.unisinos.sistema.application.service.SubsidiaryServiceImpl;
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
    PriceListServiceImpl listaPrecoServiceImpl(PriceListRepositoryPort priceListRepositoryPort,
                                               SequenceRepositoryPort sequenceRepositoryPort,
                                               SubsidiaryServicePort subsidiaryServicePort) {
        return new PriceListServiceImpl(priceListRepositoryPort, sequenceRepositoryPort, subsidiaryServicePort);
    }

    @Bean
    PaymentServiceImpl pagamentoServiceImpl(PaymentRepositoryPort pagamentoRepository,
                                            SequenceRepositoryPort sequenceRepositoryPort) {
        return new PaymentServiceImpl(pagamentoRepository, sequenceRepositoryPort);
    }
}
