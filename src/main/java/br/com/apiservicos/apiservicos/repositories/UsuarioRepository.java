package br.com.apiservicos.apiservicos.repositories;

import br.com.apiservicos.apiservicos.controller.dto.UsuarioClienteDTO;
import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    List<Usuario> tipoDeConta(PerfilUsuarioEnum tipo);
    Optional<Usuario> findByEmail(String email);
    @Query("select u from Usuario u where u.nome like concat('%',:filtro,'%') " +
            "or u.profissao like concat('%',:filtro,'%')")
    Page<Usuario> findByNomeOrProfissao(Pageable pageable, String filtro);

    @Query("select new br.com.apiservicos.apiservicos.controller.dto.UsuarioClienteDTO(u) from Usuario u where u.id = :id")
    UsuarioClienteDTO findUsuarioClienteById(Long id);

}
