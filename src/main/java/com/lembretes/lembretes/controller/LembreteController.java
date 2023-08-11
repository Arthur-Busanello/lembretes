package com.lembretes.lembretes.controller;

import com.lembretes.lembretes.dto.LembreteDTO;
import com.lembretes.lembretes.entity.Lembretes;
import com.lembretes.lembretes.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/lembretes")
public class LembreteController {

    private final LembreteService lembreteService;

    @Autowired
    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    @PostMapping
    public ResponseEntity<Lembretes> createLembrete(@RequestBody LembreteDTO lembreteDTO) {
        try {
            Lembretes createdLembrete = lembreteService.createLembrete(lembreteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLembrete);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Lembretes>> findAll() {
        try {
            List<Lembretes> lembretesList = lembreteService.findAll();
            return ResponseEntity.ok(lembretesList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lembretes> update(@PathVariable Long id, @RequestBody LembreteDTO lembreteDTO) {
        try {
            Lembretes updatedLembrete = lembreteService.updateLembrete(id, lembreteDTO);
            return ResponseEntity.ok(updatedLembrete);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            lembreteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
