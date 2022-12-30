package com.salazar.salazarmoneyapi.domain.repository;

import com.salazar.salazarmoneyapi.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

