package br.com.apiservicos.apiservicos.controller.dto;

import br.com.apiservicos.apiservicos.domain.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {

    private Long id;

    private LocalDateTime dataAvaliacao;

    private Double notaAvaliacao;
    private ServicoAvaliacaoDTO servico;
    private String descricao;

    public AvaliacaoDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
        this.notaAvaliacao = avaliacao.getNotaAvaliacao();
        this.servico = new ServicoAvaliacaoDTO(avaliacao.getServico());
        this.descricao = avaliacao.getDescricao();
    }
}
