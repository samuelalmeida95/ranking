package com.evolui.ranking.model;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    private Long idJogador;

    @NotEmpty
    @NotNull
    private String nome;
    private Long quantidadeVitorias;
    private Long partidas;
}
