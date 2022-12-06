package br.com.apiservicos.apiservicos.controller.dto;

import br.com.apiservicos.apiservicos.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarUsuarioDTO {
    private Usuario usuario;
    private String file;
}
