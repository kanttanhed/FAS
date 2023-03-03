package com.salazar.salazarmoneyapi.domain.repository;

import com.salazar.salazarmoneyapi.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
