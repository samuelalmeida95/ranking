package com.evolui.ranking.controller;

import com.evolui.ranking.controller.dto.JogadorDTO;
import com.evolui.ranking.model.Jogador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class JogadorControllerTest {

    public static final Long ID_JOGADOR = 1L;
    public static final String NOME = "Bob Esponja";
    public static final Long QUANTIDADE_VITORIAS = 10L;
    public static final Long QUANTIDADE_PARTIDAS = 12L;
    public static final int INDEX = 0;

    public static final Long ID_JOGADOR2 = 2L;
    public static final String NOME2 = "Lula Molusco";
    public static final Long QUANTIDADE_VITORIAS2 = 100L;
    public static final Long QUANTIDADE_PARTIDAS2 = 120L;
    public static final int INDEX1 = 1;

    @InjectMocks
    private JogadorController jogadorController;

    private Jogador jogador;
    private Jogador jogador2;
    private JogadorDTO jogadorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startJogador();
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void cadastrarJogador() {
    }

    @Test
    void adicionaVitoriasEPartidasDoJogador() {
    }

    @Test
    void listarTodos() {
    }

    @Test
    void listarPorVitorias() {
    }

    private void startJogador() {
        jogador = new Jogador(ID_JOGADOR, NOME, QUANTIDADE_VITORIAS, QUANTIDADE_PARTIDAS);
        jogador2 = new Jogador(ID_JOGADOR2, NOME2, QUANTIDADE_VITORIAS2, QUANTIDADE_PARTIDAS2);
    }
}