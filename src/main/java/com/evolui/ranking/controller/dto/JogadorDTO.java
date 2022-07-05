package com.evolui.ranking.controller.dto;

import com.evolui.ranking.model.Jogador;
import lombok.Data;

@Data
public class JogadorDTO {

    private Long idJogador;
    private String nome;
    private Long quantidadeVitorias;
    private Long partidas;

    public JogadorDTO(Jogador jogador){
        this.idJogador = jogador.getIdJogador();
        this.nome = jogador.getNome();
        this.quantidadeVitorias = jogador.getQuantidadeVitorias();
        this.partidas = jogador.getPartidas();
    }
}
