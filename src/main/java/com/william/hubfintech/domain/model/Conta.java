package com.william.hubfintech.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.william.hubfintech.domain.dto.TipoPessoa;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conta", schema = "hubfintech")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "nome_conta", nullable = false)
    private String nomeConta;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa;

    @Column(name = "data_criacao", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime dataCriacao;
}
