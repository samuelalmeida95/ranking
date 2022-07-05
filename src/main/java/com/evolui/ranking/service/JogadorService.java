package com.evolui.ranking.service;

import com.evolui.ranking.model.Jogador;
import com.evolui.ranking.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public Jogador findById(Long idJogador) {
        return jogadorRepository
            .findById(idJogador)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogador não encontrado!"));
    }

    public Jogador cadastrar(Jogador jogadorParaCadastrar) {
        return jogadorRepository.save(jogadorParaCadastrar);
    }
}
