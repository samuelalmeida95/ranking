package com.evolui.ranking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Jogador {

    @Id
    @Column(name = "id_jogador", nullable = false)
    private Long id_jogador;

    private String nome;

    @ManyToMany
    private List<Partida> partidasJogadas = new LinkedList<>();

}
