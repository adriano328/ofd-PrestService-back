package br.com.apiservicos.apiservicos.controller;

import br.com.apiservicos.apiservicos.controller.dto.AvaliacaoListDTO;
import br.com.apiservicos.apiservicos.domain.Avaliacao;
import br.com.apiservicos.apiservicos.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/registrar/{idServico}")
    ResponseEntity<String> registrarAvaliacao(@RequestBody Avaliacao avaliacao,
                                         @PathVariable(name = "idServico") Long idServico){
        avaliacaoService.registrarAvaliacao(avaliacao, idServico);
        return ResponseEntity.ok().body("Avaliação registrada.");
    }

    @GetMapping("/buscarAvaliacoesPorIdPrestador/{IdPrestador}")
    ResponseEntity<AvaliacaoListDTO> buscarAvaliacoesPorIdPrestador(@PathVariable(name = "IdPrestador") Long id, Pageable pageable){
        return ResponseEntity.ok().body(avaliacaoService.findAvaliacoesByIdPrestador(id, pageable));
    }
}
