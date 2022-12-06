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
public class ServicoDTO {
    private Long id;
    private LocalDateTime horarioDeAtentimento;
    private UsuarioServicoDTO cliente;
    private String descricaoServico;

    public ServicoDTO(Servico servico) {
        this.id = servico.getId();
        this.horarioDeAtentimento = servico.getHorarioDeAtentimento();
        this.cliente = new UsuarioServicoDTO(servico.getCliente());
        this.descricaoServico = servico.getDescricaoServico();

    }
}
