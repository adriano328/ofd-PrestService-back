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
public class UsuarioClienteDTO {
    private Long id;
    private String nome;
    private String imagemPerfil;
    private EnderecoDTO endereco;

    public UsuarioClienteDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.endereco = new EnderecoDTO(usuario.getEndereco());
        this.imagemPerfil = usuario.getImagemPerfil();
    }
}
