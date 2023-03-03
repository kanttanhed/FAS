package com.salazar.salazarmoneyapi.controller;

import com.salazar.salazarmoneyapi.domain.model.Pessoa;
import com.salazar.salazarmoneyapi.domain.repository.PessoaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(pessoaSalva.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(pessoaSalva);
    }

    @GetMapping("/{codigoId}")
    public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigoId) {
        return this.pessoaRepository.findById(codigoId)
                .map(peloCodigo -> ResponseEntity.ok(peloCodigo))
                .orElse(ResponseEntity.notFound().build());
    }
}
