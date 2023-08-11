package com.lembretes.lembretes.repository;

import com.lembretes.lembretes.entity.Lembretes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LembreteRepository extends JpaRepository<Lembretes,Long > {

}
