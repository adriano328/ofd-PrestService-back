package br.com.apiservicos.apiservicos.controller.dto;

import br.com.apiservicos.apiservicos.domain.Servico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicoAvaliacaoDTO {
    private Long id;
    private LocalDateTime horarioDeAtentimento;
    private UsuarioServicoDTO cliente;
    private UsuarioServicoDTO prestador;
    private String descricaoServico;

    public ServicoAvaliacaoDTO(Servico servico) {
        this.id = servico.getId();
        this.horarioDeAtentimento = servico.getHorarioDeAtentimento();
        this.prestador = new UsuarioServicoDTO(servico.getPrestador());
        this.cliente = new UsuarioServicoDTO(servico.getCliente());
        this.descricaoServico = servico.getDescricaoServico();
    }
}
