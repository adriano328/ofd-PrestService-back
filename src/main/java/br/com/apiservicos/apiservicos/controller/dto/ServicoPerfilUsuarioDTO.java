package br.com.apiservicos.apiservicos.controller.dto;

import br.com.apiservicos.apiservicos.domain.Servico;
import br.com.apiservicos.apiservicos.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicoPerfilUsuarioDTO {
    private Long id;
    private LocalDateTime horarioDeAtentimento;
    private UsuarioServicoDTO prestador;
    private String descricaoServico;
    private UsuarioServicoDTO cliente;

    public ServicoPerfilUsuarioDTO(Servico servico) {
        this.id = servico.getId();
        this.horarioDeAtentimento = servico.getHorarioDeAtentimento();
        this.prestador = new UsuarioServicoDTO(servico.getPrestador());
        this.descricaoServico = servico.getDescricaoServico();
        this.cliente = new UsuarioServicoDTO(servico.getCliente());
    }
}
