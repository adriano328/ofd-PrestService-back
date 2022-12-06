package br.com.apiservicos.apiservicos.controller;

import br.com.apiservicos.apiservicos.controller.dto.FindUsuariosByNomeAndProfissaoDTO;
import br.com.apiservicos.apiservicos.controller.dto.ResponseDTO;
import br.com.apiservicos.apiservicos.controller.dto.UsuarioClienteDTO;
import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.services.UsuarioService;
import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("salvar")
    ResponseEntity<ResponseDTO> save(@RequestBody Usuario cadastrarUsuarioDTO){
        usuarioService.save(cadastrarUsuarioDTO);
        return ResponseEntity.status(201).body(new ResponseDTO("Cadastro realizado", 201));
    }

    @GetMapping("/{id}")
    ResponseEntity<Usuario> getById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.getByID(id));
    }

    @GetMapping("/listar-por-tipo/{tipo}")
    ResponseEntity <List<Usuario>> findByTipo(@PathVariable(name = "tipo") PerfilUsuarioEnum perfilUsuarioEnum){
        return ResponseEntity.ok(usuarioService.buscaPorPerfil(perfilUsuarioEnum));
    }

    @DeleteMapping("/deletar/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/buscar-por-nome-ou-profissao")
    ResponseEntity<?> buscarTodosPorNome(Pageable pageable, @RequestParam(name = "filtro") String filtro){
        Page<FindUsuariosByNomeAndProfissaoDTO> usuarios = usuarioService.buscarTodosPorNomeOuProfissao(pageable, filtro);

        if (usuarios.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi localizado prestadores.");
        }
        return ResponseEntity.ok().body(usuarios);
    }
    @GetMapping("/buscarUsuarioClientePorId/{idUsuario}")
    ResponseEntity<UsuarioClienteDTO> buscarUsuarioClientePorId(@PathVariable(name = "idUsuario") Long id){
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioClientePorId(id));
    }

    @GetMapping("/findUsuarioByEmail")
    ResponseEntity<Usuario> findUsuarioByEmail(@RequestParam(value = "email") String email){
        return ResponseEntity.ok().body(usuarioService.buscarPorEmail(email));
    }

}
