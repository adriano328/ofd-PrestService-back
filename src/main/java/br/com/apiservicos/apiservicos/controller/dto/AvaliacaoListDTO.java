package br.com.apiservicos.apiservicos.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoListDTO {

    private UsuarioServicoDTO prestador;

    private Page<AvaliacaoDTO> avaliacoes;
}
