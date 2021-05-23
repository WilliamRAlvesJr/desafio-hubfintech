package com.william.hubfintech.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaDTO extends PessoaDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull(message = "O campo CPF não pode ser nulo!")
    @ApiModelProperty(example = "111.111.111-11")
    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)", message = "Atenção, CPF não está no formato 111.111.111-11")
    private String cpf;

    @NotNull(message = "O campo Nome Completo não pode ser nulo!")
    @ApiModelProperty(example = "João da Silva")
    private String nomeCompleto;

    @NotNull(message = "O campo Data Nascimento não pode ser nulo!")
    @ApiModelProperty(example = "1970-12-28")
    private LocalDate dataNascimento;
}
