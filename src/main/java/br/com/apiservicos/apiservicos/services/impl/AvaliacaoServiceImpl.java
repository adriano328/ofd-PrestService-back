package br.com.apiservicos.apiservicos.services.impl;

import br.com.apiservicos.apiservicos.controller.dto.AvaliacaoDTO;
import br.com.apiservicos.apiservicos.controller.dto.AvaliacaoListDTO;
import br.com.apiservicos.apiservicos.controller.dto.UsuarioServicoDTO;
import br.com.apiservicos.apiservicos.domain.Avaliacao;
import br.com.apiservicos.apiservicos.domain.Servico;
import br.com.apiservicos.apiservicos.repositories.AvaliacaoRepository;
import br.com.apiservicos.apiservicos.services.AvaliacaoService;
import br.com.apiservicos.apiservicos.services.ServicoService;
import br.com.apiservicos.apiservicos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ServicoService servicoService;

    @Override
    public void registrarAvaliacao(Avaliacao avaliacao, Long idServico) {

        Servico servico = servicoService.findSergicoById(idServico);

        avaliacao.setServico(servico);

        avaliacaoRepository.save(avaliacao);
    }

    @Override
    public AvaliacaoListDTO findAvaliacoesByIdPrestador(Long id, Pageable pageable) {
        return new AvaliacaoListDTO(new UsuarioServicoDTO(usuarioService.buscar(id)),
                avaliacaoRepository.findByServicoPrestadorId(id, pageable).map(AvaliacaoDTO::new));
    }
}
