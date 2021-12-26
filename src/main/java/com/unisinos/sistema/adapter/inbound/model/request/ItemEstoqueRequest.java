package com.unisinos.sistema.adapter.inbound.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEstoqueRequest {
    @ApiModelProperty(value = "código do item de estoque", example = "1", required = true)
    @NotBlank(message = "código do estoque deve ser preenchido")
    private String codigo;
    @ApiModelProperty(value = "nome do item de estoque", example = "tênis azul", required = true)
    @NotBlank(message = "nome do item deve ser preenchido")
    private String nome;
    @ApiModelProperty(value = "quantidade em estoque do produto", example = "12", required = true)
    @NotNull(message = "quantidade não pode ser nula")
    private Integer quantidade;
    @ApiModelProperty(value = "nome do fornecedor", example = "Nike", required = true)
    @NotBlank(message = "nome do fornecedor é obrigatório")
    private String fornecedor;
}
