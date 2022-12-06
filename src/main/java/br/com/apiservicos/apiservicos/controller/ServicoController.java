package br.com.apiservicos.apiservicos.controller;

import br.com.apiservicos.apiservicos.controller.dto.IServicoDTO;
import br.com.apiservicos.apiservicos.controller.dto.PerfilClienteDTO;
import br.com.apiservicos.apiservicos.controller.dto.PerfilPrestadorDTO;
import br.com.apiservicos.apiservicos.domain.Servico;
import br.com.apiservicos.apiservicos.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @PostMapping("/registrar")
    ResponseEntity<Servico> registrarServico(@RequestBody IServicoDTO servico){

        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.registrarServico(servico));
    }

    @GetMapping("/buscarPrestadorEServicos/{id}")
    ResponseEntity<PerfilPrestadorDTO> buscarPrestadorComServicos(@PathVariable(name = "id") Long id, Pageable pageable){
        return ResponseEntity.ok().body(servicoService.findPrestadorWithServices(id, pageable));
    }

    @GetMapping("/buscarClienteEServicos/{id}")
    ResponseEntity<PerfilClienteDTO> buscarClienteComServicos(@PathVariable(name = "id") Long id, Pageable pageable){
        return ResponseEntity.ok().body(servicoService.findClienteWithServices(id, pageable));
    }
}
