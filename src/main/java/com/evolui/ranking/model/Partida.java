package com.evolui.ranking.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Partida {

    @Id
    @Column(name = "id_partida", nullable = false)
    private Long id_partida;

    @ManyToMany
    @JoinTable(
        name = "partida_jogador",
        joinColumns = @JoinColumn(name = "id_jogador"),
        inverseJoinColumns = @JoinColumn(name = "id_partida"))
    private List<Jogador> jogadores = new LinkedList<>();

    private boolean ganhouPartida = false;
}
