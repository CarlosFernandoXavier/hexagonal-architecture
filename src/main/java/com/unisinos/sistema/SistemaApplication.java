package com.unisinos.sistema;

import com.unisinos.sistema.adapter.outbound.repository.ListaPrecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaApplication {

    @Autowired
    ListaPrecoRepository listaPrecoRepository;

    public static void main(String[] args) {
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
        SpringApplication.run(SistemaApplication.class, args);
    }

}
