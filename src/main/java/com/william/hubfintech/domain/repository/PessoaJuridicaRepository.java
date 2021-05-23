package com.william.hubfintech.domain.repository;

import com.william.hubfintech.domain.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    public Optional<PessoaJuridica> findByCnpj(String cnpj);
}
