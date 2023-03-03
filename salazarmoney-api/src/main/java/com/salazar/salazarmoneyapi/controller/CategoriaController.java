package com.salazar.salazarmoneyapi.controller;

import com.salazar.salazarmoneyapi.domain.model.Categoria;
import com.salazar.salazarmoneyapi.domain.repository.CategoriaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<?>  listarTudo() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(categoriaSalva.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    //@GetMapping("/{codigoId}")
   // public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigoId) {
    //    Optional<Categoria> peloCodigo = categoriaRepository.findById(codigoId);
    //    return peloCodigo.isPresent() ?
    //            ResponseEntity.ok(peloCodigo.get()) : ResponseEntity.notFound().build();
    //}

    @GetMapping("/{codigoId}")
    public ResponseEntity<Categoria> buscarPeloCodigoUsandoMap(@PathVariable Long codigoId) {
        return this.categoriaRepository.findById(codigoId)
                .map(peloCodigo -> ResponseEntity.ok(peloCodigo))
                .orElse(ResponseEntity.notFound().build());
    }
}