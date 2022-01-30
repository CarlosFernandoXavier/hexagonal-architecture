package com.unisinos.sistema.adapter.inbound.controller;

import com.unisinos.sistema.adapter.configuration.SwaggerConfiguration;
import com.unisinos.sistema.application.port.ReportServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/v1/relatorio")
@Api(tags = SwaggerConfiguration.RELATORIO_V1)
@AllArgsConstructor
public class RelatorioController {

    private ReportServicePort reportServicePort;

    @GetMapping(value = "/estoque", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Geração do relatório de estoque por filial")
    public ResponseEntity<Resource> citiesReport() {

        try {

            File file = reportServicePort.createSubsidiaryReport();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            var headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=" + file.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }
}
