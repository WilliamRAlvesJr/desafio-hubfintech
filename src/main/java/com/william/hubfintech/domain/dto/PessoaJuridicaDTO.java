package com.william.hubfintech.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaJuridicaDTO extends PessoaDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull(message = "O campo CNPJ não pode ser nulo!")
    @ApiModelProperty(example = "11.111.111/1111-11")
    @Pattern(regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)", message = "Atenção, o CPNJ não está no padrão 11.111.111/1111-11!")
    private String cnpj;

    @NotNull(message = "O campo Razão Social não pode ser nulo!")
    @ApiModelProperty(example = "Razão Social")
    private String razaoSocial;

    @NotNull(message = "O campo Nome Fantasia não pode ser nulo!")
    @ApiModelProperty(example = "Nome Fantasia")
    private String nomeFantasia;
}
