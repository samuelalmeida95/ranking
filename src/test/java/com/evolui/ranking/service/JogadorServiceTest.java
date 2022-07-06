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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class JogadorServiceTest {

    public static final Long ID_JOGADOR = 1L;
    public static final String NOME = "Bob Esponja";
    public static final Long QUANTIDADE_VITORIAS = 10L;
    public static final Long QUANTIDADE_PARTIDAS = 12L;
    public static final int INDEX = 0;

    @InjectMocks
    private JogadorService jogadorService;

    @Mock
    private JogadorRepository jogadorRepository;

    private Jogador jogador;
    private JogadorDTO jogadorDTO;


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
    void cadastrar() {
    }

    @Test
    void incrementaVitoriasPartidas() {
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
    void findByVitorias() {
    }

    private void startJogador() {
        jogador = new Jogador(ID_JOGADOR, NOME, QUANTIDADE_VITORIAS, QUANTIDADE_PARTIDAS);
        jogadorDTO = new JogadorDTO(ID_JOGADOR, NOME, QUANTIDADE_VITORIAS, QUANTIDADE_PARTIDAS);
    }

}