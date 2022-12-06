package br.com.apiservicos.apiservicos.services;

import br.com.apiservicos.apiservicos.controller.dto.IServicoDTO;
import br.com.apiservicos.apiservicos.controller.dto.PerfilClienteDTO;
import br.com.apiservicos.apiservicos.controller.dto.PerfilPrestadorDTO;
import br.com.apiservicos.apiservicos.domain.Servico;
import org.springframework.data.domain.Pageable;

public interface ServicoService {

    Servico registrarServico(IServicoDTO servico);
    PerfilPrestadorDTO findPrestadorWithServices(Long id, Pageable pageable);
    PerfilClienteDTO findClienteWithServices(Long id, Pageable pageable);
    Servico findSergicoById(Long id);

}
