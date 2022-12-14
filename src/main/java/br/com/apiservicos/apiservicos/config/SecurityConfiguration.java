package br.com.apiservicos.apiservicos.config;

import br.com.apiservicos.apiservicos.services.AutenticacaoService;
import br.com.apiservicos.apiservicos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AutenticacaoService autenticacaoService;
    private final UsuarioService usuarioService;

    @Autowired
    public SecurityConfiguration(AutenticacaoService autenticacaoService, UsuarioService usuarioService) {
        super();
        this.autenticacaoService = autenticacaoService;
        this.usuarioService = usuarioService;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //configurações de autentificação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configurações de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/autenticacao/autenticar").permitAll().anyRequest().authenticated()
                .and()
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AutenticacaoFilter(autenticacaoService, usuarioService), UsernamePasswordAuthenticationFilter.class);
    }

    //Configurações de recursos estáticos
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/webjars/**","/h2/**", "/usuario/salvar", "/resetPassword/**", "/usuario/findUsuarioByEmail" ,"/autenticacao/validar", "/configuration/**");
    }


}