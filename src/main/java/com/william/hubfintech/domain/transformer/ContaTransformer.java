package com.william.hubfintech.domain.transformer;

import com.william.hubfintech.domain.dto.ContaDTO;
import com.william.hubfintech.domain.dto.PessoaDTO;
import com.william.hubfintech.domain.dto.PessoaFisicaDTO;
import com.william.hubfintech.domain.dto.TipoPessoa;
import com.william.hubfintech.domain.model.Conta;
import com.william.hubfintech.domain.model.Pessoa;
import com.william.hubfintech.domain.model.PessoaFisica;
import com.william.hubfintech.domain.model.PessoaJuridica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContaTransformer {

    @Autowired
    private PessoaFisicaTransformer pessoaFisicaTransformer;

    @Autowired
    private PessoaJuridicaTransformer pessoaJuridicaTransformer;

    public Conta toEntity(ContaDTO contaDto) {
        return Conta.builder()
                .nomeConta(contaDto.getNomeConta())
                .saldo(contaDto.getSaldo())
                .dataCriacao(contaDto.getDataCriacao())
                .build();
    }

    public ContaDTO toDTO(Conta conta) {

        PessoaDTO pessoaDto = null;
        TipoPessoa tipoPessoa = null;

        if (conta.getPessoa() instanceof PessoaFisica) {
            pessoaDto = pessoaFisicaTransformer.toDTO((PessoaFisica) conta.getPessoa());
            tipoPessoa = TipoPessoa.FISICA;
        } else if (conta.getPessoa() instanceof PessoaJuridica) {
            pessoaDto = pessoaJuridicaTransformer.toDTO((PessoaJuridica) conta.getPessoa());
            tipoPessoa = TipoPessoa.JURIDICA;
        }

        return ContaDTO.builder()
                .id(conta.getId())
                .nomeConta(conta.getNomeConta())
                .saldo(conta.getSaldo())
                .dataCriacao(conta.getDataCriacao())
                .pessoaDTO(pessoaDto)
                .tipoPessoa(tipoPessoa)
                .build();
    }
}
