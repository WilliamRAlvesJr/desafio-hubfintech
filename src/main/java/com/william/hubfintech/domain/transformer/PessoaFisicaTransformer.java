package com.william.hubfintech.domain.transformer;

import com.william.hubfintech.domain.dto.PessoaFisicaDTO;
import com.william.hubfintech.domain.model.PessoaFisica;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaTransformer {

    public PessoaFisica toEntity(PessoaFisicaDTO pessoaFisicaDto) {
        return PessoaFisica.builder()
                .cpf(pessoaFisicaDto.getCpf())
                .nomeCompleto(pessoaFisicaDto.getNomeCompleto())
                .dataNascimento(pessoaFisicaDto.getDataNascimento())
                .build();
    }

    public PessoaFisicaDTO toDTO(PessoaFisica pessoaFisica) {
        return PessoaFisicaDTO.builder()
                .id(pessoaFisica.getId())
                .cpf(pessoaFisica.getCpf())
                .nomeCompleto(pessoaFisica.getNomeCompleto())
                .dataNascimento(pessoaFisica.getDataNascimento())
                .build();
    }
}
