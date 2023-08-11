package com.lembretes.lembretes.controller;

import com.lembretes.lembretes.dto.LembreteDTO;
import com.lembretes.lembretes.dto.PessoaDTO;
import com.lembretes.lembretes.entity.Pessoas;
import com.lembretes.lembretes.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoas> create(@RequestBody PessoaDTO pessoaDTO) {
        try {
            Pessoas createdPessoa = pessoaService.createPessoa(pessoaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPessoa);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/lembretes/{name}")
    public ResponseEntity<Pessoas> createLembrete(
            @PathVariable String name, @RequestBody LembreteDTO lembreteDTO) {
        try {
            Pessoas pessoaWithLembrete = pessoaService.addLembrete(name, lembreteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoaWithLembrete);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/FindByNome")
    public ResponseEntity<Optional<Optional<Pessoas>>> findByName(@RequestParam("name") final String name) {
        try {
            Optional<Optional<Pessoas>> pessoas = pessoaService.findByName(name);
            return ResponseEntity.ok(pessoas);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Pessoas>> findAll() {
        try {
            List<Pessoas> pessoas = pessoaService.findAll();
            return ResponseEntity.ok(pessoas);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoas> update(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        try {
            Pessoas updatedPessoa = pessoaService.updatePessoa(id, pessoaDTO);
            return ResponseEntity.ok(updatedPessoa);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            pessoaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
