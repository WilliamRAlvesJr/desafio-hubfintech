package com.william.hubfintech.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull(message = "O campo Nome da Conta não pode ser nulo!")
    @ApiModelProperty(example = "Nome da Conta")
    private String nomeConta;

    @ApiModelProperty(hidden = true)
    private BigDecimal saldo;

    @NotNull(message = "O campo Pessoa não pode ser nulo!")
    private PessoaDTO pessoaDTO;

    @NotNull(message = "O campo Tipo Pessoa não pode ser nulo!")
    private TipoPessoa tipoPessoa;

    @ApiModelProperty(hidden = true)
    private LocalDateTime dataCriacao;
}
