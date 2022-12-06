package br.com.apiservicos.apiservicos.controller.dto;

import br.com.apiservicos.apiservicos.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioServicoDTO {

    private Long id;
    private String nome;
    private String imagemPerfil;
    private String profissao;
    private Integer nota;
    private String descricaoPerfil;

    public UsuarioServicoDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.imagemPerfil = usuario.getImagemPerfil();
        this.profissao = usuario.getProfissao();
        this.nota = usuario.getNota();
        this.descricaoPerfil = usuario.getDescricaoPerfil();
    }
}
