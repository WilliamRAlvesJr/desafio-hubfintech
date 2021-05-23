package com.william.hubfintech.service;

import com.william.hubfintech.domain.dto.ContaDTO;
import com.william.hubfintech.domain.dto.TransferenciaDTO;
import com.william.hubfintech.domain.model.Conta;
import com.william.hubfintech.domain.model.Transferencia;
import com.william.hubfintech.domain.repository.TransferenciaRepository;
import com.william.hubfintech.domain.transformer.TransferenciaTransformer;
import com.william.hubfintech.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final TransferenciaTransformer transferenciaTransformer;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, TransferenciaTransformer transferenciaTransformer) {
        this.transferenciaRepository = transferenciaRepository;
        this.transferenciaTransformer = transferenciaTransformer;
    }

    public TransferenciaDTO create(TransferenciaDTO transferenciaDto, Conta conta) {

        List<Conta> contas = new ArrayList<>();
        contas.add(conta);

        transferenciaDto.setDataTransferencia(LocalDateTime.now());

        Transferencia transferencia = transferenciaTransformer.toEntity(transferenciaDto);
        transferencia.setContas(contas);
        Transferencia savedEntity = transferenciaRepository.save(transferencia);

        return findById(savedEntity.getId());
    }

    public List<TransferenciaDTO> selectAll() {
        return transferenciaRepository.findAll().stream().map(transferenciaTransformer::toDTO).collect(Collectors.toList());
    }

    public TransferenciaDTO findById(Long id) {
        Optional<Transferencia> entity = transferenciaRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return transferenciaTransformer.toDTO(entity.get());
    }
}
