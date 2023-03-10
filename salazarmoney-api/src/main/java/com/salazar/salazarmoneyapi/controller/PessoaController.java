package com.salazar.salazarmoneyapi.controller;

import com.salazar.salazarmoneyapi.domain.model.Categoria;
import com.salazar.salazarmoneyapi.domain.model.Pessoa;
import com.salazar.salazarmoneyapi.domain.repository.PessoaRepository;
import com.salazar.salazarmoneyapi.event.RecursoCriadoEvent;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<?>  listarTudo() {
        List<Pessoa> categorias = pessoaRepository.findAll();
        return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{codigoId}")
    public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigoId) {
        return this.pessoaRepository.findById(codigoId)
                .map(peloCodigo -> ResponseEntity.ok(peloCodigo))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{codigoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigoId ){
        pessoaRepository.deleteById(codigoId);

    }
}
