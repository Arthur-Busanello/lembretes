package com.lembretes.lembretes.service;

import com.lembretes.lembretes.dto.LembreteDTO;
import com.lembretes.lembretes.dto.PessoaDTO;
import com.lembretes.lembretes.entity.Lembretes;
import com.lembretes.lembretes.entity.Pessoas;
import com.lembretes.lembretes.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    @Autowired
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoas createPessoa(PessoaDTO pessoaDTO) {
        Pessoas pessoa = new Pessoas();
        pessoa.setName(pessoaDTO.getName());
        return repository.save(pessoa);
    }

    public Pessoas addLembrete(String name, LembreteDTO lembreteDTO) {
        Optional<Pessoas> pessoasList = repository.findByName(name);
        Pessoas pessoa = pessoasList.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Lembretes lembrete = new Lembretes();
        lembrete.setLembrete(lembreteDTO.getLembrete());

        pessoa.addLembrete(lembrete);

        return repository.save(pessoa);
    }

    public Optional<Optional<Pessoas>> findByName(String name) {
        return Optional.of(repository.findByName(name));
    }

    public List<Pessoas> findAll() {
        return repository.findAll();
    }

    public Pessoas updatePessoa(Long id, PessoaDTO pessoaDTO) {
        Pessoas existingPessoa = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa not found for id: " + id));

        existingPessoa.setName(pessoaDTO.getName());
        return repository.save(existingPessoa);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
