package com.william.hubfintech.domain.repository;

import com.william.hubfintech.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
