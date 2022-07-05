package com.evolui.ranking.controller;

import com.evolui.ranking.controller.dto.JogadorDTO;
import com.evolui.ranking.model.Jogador;
import com.evolui.ranking.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping(value = "{idJogador}")
    public ResponseEntity<JogadorDTO> buscarPorId(@PathVariable Long idJogador) {
        Jogador jogador = jogadorService.findById(idJogador);
        return ResponseEntity.ok().body(new JogadorDTO(jogador));
    }

    @PostMapping
    public ResponseEntity<JogadorDTO> cadastrarJogador(
        @RequestBody @Valid Jogador jogadorParaCadastrar,
        UriComponentsBuilder uriBuilder) {

        Jogador jogador = jogadorService.cadastrar(jogadorParaCadastrar);

        URI uri = uriBuilder
            .path("/jogadores/{id}")
            .buildAndExpand(jogadorParaCadastrar.getIdJogador())
            .toUri();

        return ResponseEntity.created(uri).body(new JogadorDTO(jogador));
    }


}
