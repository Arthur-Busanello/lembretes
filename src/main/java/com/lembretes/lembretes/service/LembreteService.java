package com.lembretes.lembretes.service;

import com.lembretes.lembretes.dto.LembreteDTO;
import com.lembretes.lembretes.entity.Lembretes;
import com.lembretes.lembretes.repository.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LembreteService {

    private final LembreteRepository repository;

    @Autowired
    public LembreteService(LembreteRepository repository) {
        this.repository = repository;
    }

    public Lembretes createLembrete(LembreteDTO lembreteDTO) {
        if (lembreteDTO.getLembrete() == null || lembreteDTO.getLembrete().trim().isEmpty()) {
            throw new IllegalArgumentException("O texto do lembrete não pode ser nulo ou vazio.");
        }

        Lembretes lembrete = new Lembretes();
        lembrete.setLembrete(lembreteDTO.getLembrete());
        return repository.save(lembrete);
    }

    public List<Lembretes> findAll() {
        return repository.findAll();
    }

    public Lembretes updateLembrete(Long id, LembreteDTO lembreteDTO) {
        if (lembreteDTO.getLembrete() == null || lembreteDTO.getLembrete().trim().isEmpty()) {
            throw new IllegalArgumentException("O texto do lembrete não pode ser nulo ou vazio.");
        }

        Lembretes existingLembrete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lembrete not found for id: " + id));

        existingLembrete.setLembrete(lembreteDTO.getLembrete());

        return repository.save(existingLembrete);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
