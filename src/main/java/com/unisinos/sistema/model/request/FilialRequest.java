package com.unisinos.sistema.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilialRequest {
    @ApiModelProperty(value = "Nome da filial", example = "G2002", required = true)
    @NotBlank(message = "nome da filial deve ser preenchido")
    private String nome;
    @Valid
    @NotNull(message = "o campo itens não pode ser nulo")
    private List<ItemEstoqueRequest> itens;
}
