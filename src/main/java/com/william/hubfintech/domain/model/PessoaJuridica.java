package com.william.hubfintech.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa_juridica", schema = "hubfintech")
public class PessoaJuridica extends Pessoa {

    @Column(name = "cnpj", updatable = false, unique = true, nullable = false)
    private String cnpj;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "name_fantasia", nullable = false)
    private String nomeFantasia;
}
