package com.evolui.ranking.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_partida;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "partida_jogador",
        joinColumns = @JoinColumn(name = "id_jogador"),
        inverseJoinColumns = @JoinColumn(name = "id_partida"))
    private List<Jogador> jogadores = new LinkedList<>();

    private boolean ganhouPartida = false;
}
