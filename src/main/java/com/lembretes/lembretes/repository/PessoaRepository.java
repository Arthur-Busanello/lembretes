package com.lembretes.lembretes.repository;

import com.lembretes.lembretes.entity.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoas, Long> {
    Optional<Pessoas> findByName(@Param("name") final String name);
}
