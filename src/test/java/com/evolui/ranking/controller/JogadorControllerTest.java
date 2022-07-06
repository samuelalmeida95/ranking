package com.evolui.ranking.controller;

import com.evolui.ranking.controller.dto.JogadorDTO;
import com.evolui.ranking.model.Jogador;
import com.evolui.ranking.service.JogadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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

    @Mock
    private JogadorService jogadorService;

    private Jogador jogador;
    private Jogador jogador2;
    private JogadorDTO jogadorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startJogador();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(jogadorService.findById(anyLong())).thenReturn(jogador);

        ResponseEntity<JogadorDTO> response = jogadorController.buscarPorId(ID_JOGADOR);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(JogadorDTO.class, response.getBody().getClass());

        assertEquals(ID_JOGADOR, response.getBody().getIdJogador());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(QUANTIDADE_PARTIDAS, response.getBody().getPartidas());
        assertEquals(QUANTIDADE_VITORIAS, response.getBody().getQuantidadeVitorias());

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