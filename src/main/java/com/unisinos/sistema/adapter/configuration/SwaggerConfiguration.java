package com.unisinos.sistema.adapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static final String LISTA_PRECO_V1 = "listaPreco v1";
    public static final String ITEM_V1 = "item v1";
    public static final String RELATORIO_V1 = "relatorio v1";
    public static final String PAGAMENTO_V1 = "pagamento v1";
    public static final String FILIAL_V1 = "filial v1";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.unisinos.sistema"))
                .paths(regex("/.*"))
                .build()
                .useDefaultResponseMessages(false)
                .tags(createTag(LISTA_PRECO_V1, "listaPreco v1"),
                        createTag(ITEM_V1, "item v1"),
                        createTag(RELATORIO_V1, "relatorio v1"),
                        createTag(PAGAMENTO_V1, "pagamento v1"),
                        createTag(FILIAL_V1, "filial v1"));
    }

    private Tag createTag(String name, String description) {
        return new Tag(name, description);
    }
}
