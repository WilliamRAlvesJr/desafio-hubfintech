package com.william.hubfintech.domain.transformer;

import com.william.hubfintech.domain.dto.ContaDTO;
import com.william.hubfintech.domain.dto.TransferenciaDTO;
import com.william.hubfintech.domain.model.Conta;
import com.william.hubfintech.domain.model.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TransferenciaTransformer {

    @Autowired
    private ContaTransformer contaTransformer;

    public Transferencia toEntity(TransferenciaDTO transferenciaDTO) {

        List<Conta> contas = new ArrayList<>();
        if (Objects.nonNull(transferenciaDTO.getContaDtos())) {
            for (ContaDTO contaDto : transferenciaDTO.getContaDtos()) {
                contas.add(contaTransformer.toEntity(contaDto));
            }
        }

        return Transferencia.builder()
                .credito(transferenciaDTO.getCredito())
                .dataTransferencia(transferenciaDTO.getDataTransferencia())
                .sumario(transferenciaDTO.getSumario())
                .contas(contas)
                .build();
    }

    public TransferenciaDTO toDTO(Transferencia transferencia) {

        List<ContaDTO> contaDtos = new ArrayList<>();
        for (Conta conta : transferencia.getContas()) {
            contaDtos.add(contaTransformer.toDTO(conta));
        }

        return TransferenciaDTO.builder()
                .id(transferencia.getId())
                .credito(transferencia.getCredito())
                .dataTransferencia(transferencia.getDataTransferencia())
                .sumario(transferencia.getSumario())
                .contaDtos(contaDtos)
                .build();
    }
}
