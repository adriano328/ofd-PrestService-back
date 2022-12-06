package br.com.apiservicos.apiservicos.services.impl;

import br.com.apiservicos.apiservicos.controller.dto.FindUsuariosByNomeAndProfissaoDTO;
import br.com.apiservicos.apiservicos.controller.dto.UsuarioClienteDTO;
import br.com.apiservicos.apiservicos.domain.ResetPasswordToken;
import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.exceptions.RegistroNaoEncontradoException;
import br.com.apiservicos.apiservicos.exceptions.RegraDeNegocioException;
import br.com.apiservicos.apiservicos.repositories.UsuarioRepository;
import br.com.apiservicos.apiservicos.services.ResetPasswordTokenService;
import br.com.apiservicos.apiservicos.services.S3AwsService;
import br.com.apiservicos.apiservicos.services.UsuarioService;
import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    ResetPasswordTokenService resetPasswordService;

    @Autowired
    private S3AwsService s3AwsService;

    @Override
    public void save(Usuario usuario) {

        verificaSeUsuarioNaoEstaVazio(usuario);
        verificaSeJaExisteEmailNaBase(usuario);
        criptografaPassword(usuario);
        verificaSeFileNaoEstaVazio(usuario.getImagemPerfil());

        try {
            usuario.setImagemPerfil(uploadImagemS3(usuario.getImagemPerfil()));
            usuarioRepository.save(usuario);
            log.info("Usuário cadastrado.");
        } catch (Exception e){
            log.info("Ouve um erro: " + e);
        }
    }

    @Override
    public Usuario getByID(Long id) {
        return usuarioRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Usuário não localizado!"));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RegistroNaoEncontradoException("usuario.naoEncontrado"));
    }

    @Override
    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("usuario.naoEncontrado"));
    }

    public ResetPasswordToken generateResetPasswordToken(Usuario obj) {
        Usuario user = this.buscar(obj.getId());
        ResetPasswordToken resetToken = new ResetPasswordToken();

        if(user != null) {
            resetToken.setUser(user);
            Date createdDate = new Date();
            resetToken.setToken(UUID.randomUUID().toString());
            resetToken.setCreatedDate(createdDate);
            resetPasswordService.insert(resetToken);
        }
        return resetToken;
    }

    public void updatePassword(Usuario usuario){
        Usuario usuarioFind = buscar(usuario.getId());
        usuarioFind.setSenha(encoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuarioFind);
    }

    @Override
    public List<Usuario> buscaPorPerfil(PerfilUsuarioEnum perfilUsuarioEnum) {
        return  usuarioRepository.tipoDeConta(perfilUsuarioEnum);
    }

    public void excluir(Long id){
        usuarioRepository.delete(buscar(id));
    }

    @Override
    public Page<FindUsuariosByNomeAndProfissaoDTO> buscarTodosPorNomeOuProfissao(Pageable pageable, String filtro) {
        List<FindUsuariosByNomeAndProfissaoDTO> usuarios = usuarioRepository.findByNomeOrProfissao(pageable, filtro)
                .map(FindUsuariosByNomeAndProfissaoDTO::new)
                .stream().filter(usuario -> usuario.getTipoDeConta().equals(PerfilUsuarioEnum.PRESTADOR))
                .collect(Collectors.toList());
        return new PageImpl<>(usuarios);
    }

    @Override
    public UsuarioClienteDTO buscarUsuarioClientePorId(Long id) {
        return usuarioRepository.findUsuarioClienteById(id);
    }

    void verificaSeUsuarioNaoEstaVazio(Usuario usuario){
        if(StringUtils.isAnyBlank(usuario.getEmail(), usuario.getNome(), usuario.getSenha(), usuario.getTelefone(),
                usuario.getTipoDeConta().toString(), usuario.getEndereco().getCep(), usuario.getEndereco().getBairro(),
                usuario.getEndereco().getCidade(), usuario.getEndereco().getEstado())){
            throw new RegraDeNegocioException("usuario.invalido");
        }
    }

    void verificaSeJaExisteEmailNaBase(Usuario usuario){
        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new RegraDeNegocioException("usuario.jaExists");
        }
    }

    void criptografaPassword(Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
    }

    void verificaSeFileNaoEstaVazio(String file){
        if(file.isBlank()){
            throw new RegraDeNegocioException("arquivo.vazio");
        }
    }

    String uploadImagemS3(String base64) throws IOException {
        return s3AwsService.uploadFile(base64);
    }
}
