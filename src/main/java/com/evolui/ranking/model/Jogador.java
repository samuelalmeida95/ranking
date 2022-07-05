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
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_jogador;

    private String nome;

    @ManyToMany(mappedBy = "jogadores", cascade = CascadeType.ALL)
    private List<Partida> partidasJogadas = new LinkedList<>();
}
