package com.evolui.ranking.controller;

import com.evolui.ranking.controller.dto.JogadorDTO;
import com.evolui.ranking.model.Jogador;
import com.evolui.ranking.service.JogadorService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        startJogador();

        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @After("")
    public void teardown() {
        RequestContextHolder.resetRequestAttributes();
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
        assertEquals(QUANTIDADE_PARTIDAS, response.getBody().getQuantidadePartidas());
        assertEquals(QUANTIDADE_VITORIAS, response.getBody().getQuantidadeVitorias());

    }

    @Test
    void whenCreateThenReturnCreated() {
        when(jogadorService.cadastrar(any())).thenReturn(jogador);

        ResponseEntity<JogadorDTO> response = jogadorController.cadastrarJogador(jogador);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        when(jogadorService.findAll()).thenReturn(List.of(jogador));

        ResponseEntity<List<JogadorDTO>> response = jogadorController.listarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(JogadorDTO.class, Objects.requireNonNull(response.getBody()).get(INDEX).getClass());

        assertEquals(ID_JOGADOR, response.getBody().get(INDEX).getIdJogador());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(QUANTIDADE_PARTIDAS, response.getBody().get(INDEX).getQuantidadePartidas());
        assertEquals(QUANTIDADE_VITORIAS, response.getBody().get(INDEX).getQuantidadeVitorias());
    }

    @Test
    void whenFindByVitoriasSuccess() {
        when(jogadorService.findAllByVitorias()).thenReturn(List.of(jogador, jogador2));

        ResponseEntity<List<JogadorDTO>> response = jogadorController.listarPorVitorias();

        assertNotNull(response);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(ID_JOGADOR, response.getBody().get(INDEX).getIdJogador());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(QUANTIDADE_VITORIAS, response.getBody().get(INDEX).getQuantidadeVitorias());
        assertEquals(QUANTIDADE_PARTIDAS, response.getBody().get(INDEX).getQuantidadePartidas());

        assertEquals(ID_JOGADOR2, response.getBody().get(INDEX1).getIdJogador());
        assertEquals(NOME2, response.getBody().get(INDEX1).getNome());
        assertEquals(QUANTIDADE_VITORIAS2, response.getBody().get(INDEX1).getQuantidadeVitorias());
        assertEquals(QUANTIDADE_PARTIDAS2, response.getBody().get(INDEX1).getQuantidadePartidas());

        assertTrue(response.getBody().get(INDEX1).getQuantidadeVitorias() > response.getBody().get(INDEX).getQuantidadeVitorias());
        assertTrue(response.getBody().get(INDEX).getQuantidadeVitorias() < response.getBody().get(INDEX1).getQuantidadeVitorias());
        assertNotEquals(response.getBody().get(INDEX).getQuantidadeVitorias(), response.getBody().get(INDEX1).getQuantidadeVitorias());
    }

    private void startJogador() {
        jogador = new Jogador(ID_JOGADOR, NOME, QUANTIDADE_VITORIAS, QUANTIDADE_PARTIDAS);
        jogador2 = new Jogador(ID_JOGADOR2, NOME2, QUANTIDADE_VITORIAS2, QUANTIDADE_PARTIDAS2);
    }
}