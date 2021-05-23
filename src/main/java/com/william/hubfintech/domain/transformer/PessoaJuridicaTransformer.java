package com.william.hubfintech.domain.transformer;

import com.william.hubfintech.domain.dto.PessoaJuridicaDTO;
import com.william.hubfintech.domain.model.PessoaFisica;
import com.william.hubfintech.domain.model.PessoaJuridica;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaTransformer {

    public PessoaJuridica toEntity(PessoaJuridicaDTO pessoaJuridicaDto) {
        return PessoaJuridica.builder()
                .cnpj(pessoaJuridicaDto.getCnpj())
                .nomeFantasia(pessoaJuridicaDto.getNomeFantasia())
                .razaoSocial(pessoaJuridicaDto.getRazaoSocial())
                .build();
    }

    public PessoaJuridicaDTO toDTO(PessoaJuridica pessoaJuridica) {
        return PessoaJuridicaDTO.builder()
                .id(pessoaJuridica.getId())
                .cnpj(pessoaJuridica.getCnpj())
                .nomeFantasia(pessoaJuridica.getNomeFantasia())
                .razaoSocial(pessoaJuridica.getRazaoSocial())
                .build();
    }
}
