package com.william.hubfintech.domain.repository;

import com.william.hubfintech.domain.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

    public Optional<PessoaFisica> findByCpf(String cpf);
}
