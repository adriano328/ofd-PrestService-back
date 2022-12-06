package br.com.apiservicos.apiservicos.services.impl;

import br.com.apiservicos.apiservicos.controller.dto.IServicoDTO;
import br.com.apiservicos.apiservicos.controller.dto.PerfilClienteDTO;
import br.com.apiservicos.apiservicos.controller.dto.PerfilPrestadorDTO;
import br.com.apiservicos.apiservicos.controller.dto.ServicoPerfilUsuarioDTO;
import br.com.apiservicos.apiservicos.controller.dto.UsuarioServicoDTO;
import br.com.apiservicos.apiservicos.domain.Servico;
import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.exceptions.RegraDeNegocioException;
import br.com.apiservicos.apiservicos.repositories.ServicoRepository;
import br.com.apiservicos.apiservicos.services.ServicoService;
import br.com.apiservicos.apiservicos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceServicoImpl implements ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Override
    public Servico registrarServico(IServicoDTO servico) {

        Servico servicoSave = Servico.builder()
                .descricaoServico(servico.getDescricaoServico())
                .cliente(usuarioService.buscar(Long.parseLong(servico.getCliente())))
                .horarioDeAtentimento(servico.getHorarioDeAtentimento())
                .prestador(usuarioService.buscar(servico.getPrestador().getId()))
                .id(servico.getId())
                .build();


        return servicoRepository.save(servicoSave);
    }

    @Override
    public PerfilPrestadorDTO findPrestadorWithServices(Long id, Pageable pageable) {
        return new PerfilPrestadorDTO(new UsuarioServicoDTO(usuarioService.buscar(id)),
                servicoRepository.findAllByPrestadorId(id, pageable).map(ServicoPerfilUsuarioDTO::new));
    }
    @Override
    public PerfilClienteDTO findClienteWithServices(Long id, Pageable pageable) {
        return new PerfilClienteDTO(new UsuarioServicoDTO(usuarioService.buscar(id)),
                servicoRepository.findAllByClienteId(id, pageable).map(ServicoPerfilUsuarioDTO::new));
    }

    @Override
    public Servico findSergicoById(Long id) {
        return servicoRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("servico.naoEncontrado"));
    }
}
