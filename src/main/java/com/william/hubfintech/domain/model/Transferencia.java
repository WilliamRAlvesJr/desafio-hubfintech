package com.william.hubfintech.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.engine.internal.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfer", schema = "hubfintech")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "credito", nullable = false)
    private BigDecimal credito;

    @Column(name = "sumario")
    private String sumario;

    @ManyToMany
    @JoinColumn(name = "conta_id")
    private List<Conta> contas = new ArrayList<>();

    @Column(name = "data_transferencia", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime dataTransferencia;
}
