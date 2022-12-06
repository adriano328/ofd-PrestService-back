package br.com.apiservicos.apiservicos.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilPrestadorDTO {
    private UsuarioServicoDTO prestador;
    Page<ServicoPerfilUsuarioDTO> servicos;
}
