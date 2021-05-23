package com.william.hubfintech.domain.dto;

import com.william.hubfintech.domain.model.Conta;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private BigDecimal credito;

    private String sumario;

    @ApiModelProperty(hidden = true)
    private List<ContaDTO> contaDtos;

    @ApiModelProperty(hidden = true)
    private LocalDateTime dataTransferencia;
}
