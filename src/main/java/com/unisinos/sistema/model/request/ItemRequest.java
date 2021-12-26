package com.unisinos.sistema.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemRequest {
    @ApiModelProperty(value = "código do item", example = "12356", required = true)
    @NotBlank(message = "codigo deve ser preenchido")
    private String codigo;
    @ApiModelProperty(value = "nome do item", example = "Chuteira de couro", required = true)
    @NotBlank(message = "nome do item deve ser preenchido")
    private String nome;
    @ApiModelProperty(value = "preço do item", example = "127.56", required = true)
    @NotNull(message = "preço deve ser preenchido")
    private BigDecimal preco;
}