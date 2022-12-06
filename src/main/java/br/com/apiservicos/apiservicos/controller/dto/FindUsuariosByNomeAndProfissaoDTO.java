package br.com.apiservicos.apiservicos.controller.dto;

import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindUsuariosByNomeAndProfissaoDTO {
    private Long id;
    private String nome_imagem;
    private Integer nota;
    private String nome;
    private String profissao;
    private PerfilUsuarioEnum tipoDeConta;

    public FindUsuariosByNomeAndProfissaoDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.profissao = usuario.getProfissao();
        this.nome_imagem = usuario.getImagemPerfil();
        this.nota = usuario.getNota();
        this.tipoDeConta = usuario.getTipoDeConta();
    }
}
