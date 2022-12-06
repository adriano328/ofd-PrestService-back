package br.com.apiservicos.apiservicos.services;

import br.com.apiservicos.apiservicos.controller.dto.FindUsuariosByNomeAndProfissaoDTO;
import br.com.apiservicos.apiservicos.controller.dto.UsuarioClienteDTO;
import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {

    void save(Usuario usuario);

    Usuario getByID(Long id);

    Usuario buscarPorEmail(String email);

    Usuario buscar(Long id);

    List<Usuario> buscaPorPerfil(PerfilUsuarioEnum perfilUsuarioEnum);

    void excluir(Long id);

    Page<FindUsuariosByNomeAndProfissaoDTO> buscarTodosPorNomeOuProfissao(Pageable pageable, String nome);

    UsuarioClienteDTO buscarUsuarioClientePorId(Long id);
}
