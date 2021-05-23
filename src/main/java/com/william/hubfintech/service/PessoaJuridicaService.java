package com.william.hubfintech.service;

import com.william.hubfintech.domain.dto.PessoaFisicaDTO;
import com.william.hubfintech.domain.dto.PessoaJuridicaDTO;
import com.william.hubfintech.domain.model.PessoaFisica;
import com.william.hubfintech.domain.model.PessoaJuridica;
import com.william.hubfintech.domain.repository.PessoaJuridicaRepository;
import com.william.hubfintech.domain.transformer.PessoaJuridicaTransformer;
import com.william.hubfintech.exceptions.ResourceAlreadyExistsException;
import com.william.hubfintech.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaJuridicaService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final PessoaJuridicaTransformer pessoaJuridicaTransformer;

    public PessoaJuridicaService(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaJuridicaTransformer pessoaJuridicaTransformer) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaJuridicaTransformer = pessoaJuridicaTransformer;
    }

    public PessoaJuridicaDTO create(PessoaJuridicaDTO pessoaJuridicaDto) {

        if (pessoaJuridicaRepository.findByCnpj(pessoaJuridicaDto.getCnpj()).isPresent())
            throw new ResourceAlreadyExistsException();

        PessoaJuridica entity = pessoaJuridicaTransformer.toEntity(pessoaJuridicaDto);
        PessoaJuridica savedEntity = pessoaJuridicaRepository.save(entity);

        return findByCpf(savedEntity.getCnpj());
    }

    public List<PessoaJuridicaDTO> selectAll() {
        return pessoaJuridicaRepository.findAll().stream().map(pessoaJuridicaTransformer::toDTO).collect(Collectors.toList());
    }

    public PessoaJuridicaDTO findByCpf(String cnpj) {
        Optional<PessoaJuridica> entity = pessoaJuridicaRepository.findByCnpj(cnpj);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return pessoaJuridicaTransformer.toDTO(entity.get());
    }

    public PessoaJuridica findById(Long id) {
        Optional<PessoaJuridica> entity = pessoaJuridicaRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return entity.get();
    }
}
