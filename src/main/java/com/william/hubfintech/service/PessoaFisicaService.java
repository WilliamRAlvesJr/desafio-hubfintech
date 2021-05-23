package com.william.hubfintech.service;

import com.william.hubfintech.domain.dto.PessoaFisicaDTO;
import com.william.hubfintech.domain.model.PessoaFisica;
import com.william.hubfintech.domain.repository.PessoaFisicaRepository;
import com.william.hubfintech.domain.transformer.PessoaFisicaTransformer;
import com.william.hubfintech.exceptions.ResourceAlreadyExistsException;
import com.william.hubfintech.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaFisicaService {

    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaFisicaTransformer pessoaFisicaTransformer;

    public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository, PessoaFisicaTransformer pessoaFisicaTransformer) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaFisicaTransformer = pessoaFisicaTransformer;
    }

    public PessoaFisicaDTO create(PessoaFisicaDTO pessoaFisicaDto) {

        if (pessoaFisicaRepository.findByCpf(pessoaFisicaDto.getCpf()).isPresent())
            throw new ResourceAlreadyExistsException();

        PessoaFisica entity = pessoaFisicaTransformer.toEntity(pessoaFisicaDto);
        PessoaFisica savedEntity = pessoaFisicaRepository.save(entity);

        return findByCpf(savedEntity.getCpf());
    }

    public List<PessoaFisicaDTO> selectAll() {
        return pessoaFisicaRepository.findAll().stream().map(pessoaFisicaTransformer::toDTO).collect(Collectors.toList());
    }

    public PessoaFisicaDTO findByCpf(String cpf) {
        Optional<PessoaFisica> entity = pessoaFisicaRepository.findByCpf(cpf);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return pessoaFisicaTransformer.toDTO(entity.get());
    }

    public PessoaFisica findById(Long id) {
        Optional<PessoaFisica> entity = pessoaFisicaRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return entity.get();
    }
}
