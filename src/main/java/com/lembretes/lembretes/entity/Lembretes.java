package com.lembretes.lembretes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "lembretes")
public class Lembretes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lembrete")
    private String lembrete;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoas pessoa;

    public void setPessoa(Pessoas pessoa) {
        if (this.pessoa != null) {
            this.pessoa.getLembretes().remove(this);
        }
        this.pessoa = pessoa;
        if (pessoa != null) {
            pessoa.getLembretes().add(this);
        }
    }
}
