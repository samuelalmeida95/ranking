package com.evolui.ranking.service;

import com.evolui.ranking.controller.dto.JogadorDTO;
import com.evolui.ranking.model.Jogador;
import com.evolui.ranking.repository.JogadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class JogadorServiceTest {

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
    private JogadorService jogadorService;

    @Mock
    private JogadorRepository jogadorRepository;

    private Jogador jogador;
    private Jogador jogador2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startJogador();
    }

    @Test
    void whenFindByIdThenReturnJogadorInstance() {
        when(jogadorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(jogador));

        Jogador response = jogadorService.findById(ID_JOGADOR);

        assertNotNull(response);
        assertEquals(Jogador.class, response.getClass());
        assertEquals(ID_JOGADOR, response.getIdJogador());
        assertEquals(NOME, response.getNome());
        assertEquals(QUANTIDADE_PARTIDAS, response.getQuantidadePartidas());
        assertEquals(QUANTIDADE_VITORIAS, response.getQuantidadeVitorias());
    }

    @Test
    void whenFindByIdThenReturnAnResponseStatusException() {
        when(jogadorRepository.findById(anyLong()))
            .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogador não encontrado!"));

        try {
            jogadorService.findById(ID_JOGADOR);
        } catch (Exception e) {
            assertEquals(ResponseStatusException.class, e.getClass());
        }
    }

    @Test
    void whenCreateReturnSuccess() {
        when(jogadorRepository.save(any())).thenReturn(jogador);

        Jogador response = jogadorService.cadastrar(jogador);

        assertNotNull(response);
        assertEquals(Jogador.class, response.getClass());
        assertEquals(Jogador.class, response.getClass());
        assertEquals(ID_JOGADOR, response.getIdJogador());
        assertEquals(NOME, response.getNome());
        assertEquals(QUANTIDADE_PARTIDAS, response.getQuantidadePartidas());
        assertEquals(QUANTIDADE_VITORIAS, response.getQuantidadeVitorias());
    }

    @Test
    void whenIncrementWinsGamesSuccess() {
        when(jogadorRepository.save(any())).thenReturn(jogador);
        when(jogadorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(jogador));
        Jogador response = jogadorService.incrementaVitoriasPartidas(ID_JOGADOR, 20L, 20L);

        assertNotNull(response);
        assertEquals(ID_JOGADOR, response.getIdJogador());
        assertEquals(NOME, response.getNome());
        assertEquals(20L, response.getQuantidadePartidas());
        assertEquals(20L, response.getQuantidadeVitorias());
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(jogadorRepository.findAll()).thenReturn(List.of(jogador));

        List<Jogador> response = jogadorService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Jogador.class, response.get(INDEX).getClass());

        assertEquals(ID_JOGADOR, response.get(INDEX).getIdJogador());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(QUANTIDADE_VITORIAS, response.get(INDEX).getQuantidadeVitorias());
        assertEquals(QUANTIDADE_PARTIDAS, response.get(INDEX).getQuantidadePartidas());
    }

    @Test
    void whenFindByVitoriasSuccess() {
        when(jogadorRepository.findAllByOrderByQuantidadeVitoriasAsc()).thenReturn(List.of(jogador, jogador2));

        List<Jogador> response = jogadorService.findAllByVitorias();
        assertNotNull(response);
        assertEquals(2, response.size());

        assertEquals(ID_JOGADOR, response.get(INDEX).getIdJogador());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(QUANTIDADE_VITORIAS, response.get(INDEX).getQuantidadeVitorias());
        assertEquals(QUANTIDADE_PARTIDAS, response.get(INDEX).getQuantidadePartidas());

        assertEquals(ID_JOGADOR2, response.get(INDEX1).getIdJogador());
        assertEquals(NOME2, response.get(INDEX1).getNome());
        assertEquals(QUANTIDADE_VITORIAS2, response.get(INDEX1).getQuantidadeVitorias());
        assertEquals(QUANTIDADE_PARTIDAS2, response.get(INDEX1).getQuantidadePartidas());

        assertTrue(response.get(INDEX1).getQuantidadeVitorias() > response.get(INDEX).getQuantidadeVitorias());
        assertTrue(response.get(INDEX).getQuantidadeVitorias() < response.get(INDEX1).getQuantidadeVitorias());
        assertNotEquals(response.get(INDEX).getQuantidadeVitorias(), response.get(INDEX1).getQuantidadeVitorias());
    }

    private void startJogador() {
        jogador = new Jogador(ID_JOGADOR, NOME, QUANTIDADE_VITORIAS, QUANTIDADE_PARTIDAS);
        jogador2 = new Jogador(ID_JOGADOR2, NOME2, QUANTIDADE_VITORIAS2, QUANTIDADE_PARTIDAS2);
    }

}