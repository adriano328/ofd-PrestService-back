package br.com.apiservicos.apiservicos.services;

import br.com.apiservicos.apiservicos.controller.dto.AvaliacaoListDTO;
import br.com.apiservicos.apiservicos.domain.Avaliacao;
import org.springframework.data.domain.Pageable;

public interface AvaliacaoService {

    void registrarAvaliacao(Avaliacao avaliacao, Long idServico);

    AvaliacaoListDTO findAvaliacoesByIdPrestador(Long id, Pageable pageable);
}
