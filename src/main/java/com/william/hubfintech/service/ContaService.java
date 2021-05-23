package com.william.hubfintech.service;

import com.william.hubfintech.domain.dto.*;
import com.william.hubfintech.domain.model.Conta;
import com.william.hubfintech.domain.model.Transferencia;
import com.william.hubfintech.domain.repository.ContaRepository;
import com.william.hubfintech.domain.repository.TransferenciaRepository;
import com.william.hubfintech.domain.transformer.ContaTransformer;
import com.william.hubfintech.domain.transformer.TransferenciaTransformer;
import com.william.hubfintech.exceptions.InvalidValueException;
import com.william.hubfintech.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaTransformer contaTransformer;
    private final TransferenciaService transferenciaService;
    private final PessoaFisicaService pessoaFisicaService;
    private final PessoaJuridicaService pessoaJuridicaService;
    private final TransferenciaRepository transferenciaRepository;
    private final TransferenciaTransformer transferenciaTransformer;

    public ContaService(ContaRepository contaRepository, ContaTransformer contaTransformer,
                        TransferenciaService transferenciaService, PessoaFisicaService pessoaFisicaService,
                        PessoaJuridicaService pessoaJuridicaService, TransferenciaRepository transferenciaRepository, TransferenciaTransformer transferenciaTransformer) {
        this.contaRepository = contaRepository;
        this.contaTransformer = contaTransformer;
        this.transferenciaService = transferenciaService;
        this.pessoaFisicaService = pessoaFisicaService;
        this.pessoaJuridicaService = pessoaJuridicaService;
        this.transferenciaRepository = transferenciaRepository;
        this.transferenciaTransformer = transferenciaTransformer;
    }

    public ContaDTO create(ContaDTO contaDto) {

        contaDto.setDataCriacao(LocalDateTime.now());
        contaDto.setSaldo(BigDecimal.ZERO);

        Conta entity = contaTransformer.toEntity(contaDto);

        if (contaDto.getPessoaDTO().getId() == null)
            throw new ResourceNotFoundException();

        if (contaDto.getTipoPessoa() == TipoPessoa.FISICA) {
            entity.setPessoa(pessoaFisicaService.findById(contaDto.getPessoaDTO().getId()));
        } else if (contaDto.getTipoPessoa() == TipoPessoa.JURIDICA) {
            entity.setPessoa(pessoaJuridicaService.findById(contaDto.getPessoaDTO().getId()));
        } else {
            throw new ResourceNotFoundException();
        }

        Conta savedEntity = contaRepository.save(entity);

        return findById(savedEntity.getId());
    }

    public ContaDTO insertCredit(Long id, TransferenciaDTO transferenciaDTO) {

        Optional<Conta> conta = contaRepository.findById(id);

        if (!conta.isPresent()) {
            throw new ResourceNotFoundException();
        }

        if (contaTransformer.toDTO(conta.get()).getTipoPessoa() == TipoPessoa.FISICA) {
            if (transferenciaDTO.getCredito().compareTo(BigDecimal.valueOf(500.00)) > 0.00) {
                throw new InvalidValueException("Atenção, não é permitido depositar valores maiores que 500.00 para pessoas físicas");
            }
        } else {
            if (transferenciaDTO.getCredito().compareTo(BigDecimal.valueOf(2000.00)) > 0.00) {
                throw new InvalidValueException("Atenção, não é permitido depositar valores maiores que 2000.00");
            }
        }

        conta.get().setSaldo(conta.get().getSaldo().add(transferenciaDTO.getCredito()));
        Conta savedEntity = contaRepository.save(conta.get());

        transferenciaService.create(transferenciaDTO, savedEntity);

        return findById(savedEntity.getId());
    }

    public List<ContaDTO> selectAll() {
        return contaRepository.findAll().stream().map(contaTransformer::toDTO).collect(Collectors.toList());
    }

    public ContaDTO findById(Long id) {
        Optional<Conta> entity = contaRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return contaTransformer.toDTO(entity.get());
    }
}
