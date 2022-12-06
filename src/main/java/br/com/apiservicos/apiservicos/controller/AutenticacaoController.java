package br.com.apiservicos.apiservicos.controller;

import antlr.TokenStreamHiddenTokenFilter;
import br.com.apiservicos.apiservicos.controller.dto.LoginDTO;
import br.com.apiservicos.apiservicos.controller.dto.TokenDTO;
import br.com.apiservicos.apiservicos.exceptions.RegraDeNegocioException;
import br.com.apiservicos.apiservicos.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/autenticacao")
public class AutenticacaoController {

    public static final String SENHA_INCORRETA = "Senha incorreta.";
    public static final String EMAIL_INCORRETO = "Email incorreto.";
    public static final String BAD_CREDENTIALS = "Bad credentials";
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("autenticar")
    public ResponseEntity<?> stored(@RequestBody @Valid LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());

        try {
            authenticationManager.authenticate(login).isAuthenticated();
        }catch (Exception e){
                if(e.getMessage().equals(BAD_CREDENTIALS)){
                    return ResponseEntity.status(401).body(SENHA_INCORRETA);
                } else {
                    return ResponseEntity.status(401).body(EMAIL_INCORRETO);
                }
            }

        TokenDTO tokenDTO = new TokenDTO("Bearer", autenticacaoService.getToken(login));

        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("validar")
    public ResponseEntity<Boolean> isValid(@RequestBody TokenDTO token){
        return ResponseEntity.ok(autenticacaoService.isTokenValid(token.getToken()));
    }

}