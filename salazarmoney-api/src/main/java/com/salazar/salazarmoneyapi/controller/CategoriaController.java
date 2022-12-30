package com.salazar.salazarmoneyapi.controller;

import com.salazar.salazarmoneyapi.domain.model.Categoria;
import com.salazar.salazarmoneyapi.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listarTudo() {
        return categoriaRepository.findAll();
    }

}