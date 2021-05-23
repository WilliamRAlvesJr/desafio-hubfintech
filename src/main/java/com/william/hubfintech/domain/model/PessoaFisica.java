package com.william.hubfintech.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa_fisica", schema = "hubfintech")
public class PessoaFisica extends Pessoa {

    @Column(name = "cpf", updatable = false, unique = true, nullable = false)
    private String cpf;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "data_nascimento", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dataNascimento;
}
