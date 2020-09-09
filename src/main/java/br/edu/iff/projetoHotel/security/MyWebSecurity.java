package br.edu.iff.projetoHotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private FuncionarioDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/apirest/**")
                .and()
                .authorizeRequests()
                .antMatchers("/apirest/**").hasRole("ADMIN")
                .antMatchers("/files/**").hasAnyRole("ADMIN", "FUNC")
                .antMatchers("/funcionarios/meusdados/**").hasAnyRole("ADMIN", "FUNC")
                .antMatchers("/funcionarios").hasRole("ADMIN")
                .antMatchers("/funcionarios/funcionario").hasRole("ADMIN")
                .antMatchers("/funcionarios/funcionario/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN", "FUNC")                
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .usernameParameter("email");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }

}
