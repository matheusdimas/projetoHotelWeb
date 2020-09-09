package br.edu.iff.projetoHotel.security;

import br.edu.iff.projetoHotel.model.Funcionario;
import br.edu.iff.projetoHotel.model.Permissao;
import br.edu.iff.projetoHotel.repository.FuncionarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioDetailsService implements UserDetailsService{

    @Autowired
    private FuncionarioRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Funcionario funcionario = repo.findByEmail(email);
        if(funcionario==null){
            throw new UsernameNotFoundException("Funcionário não encontrado com esse email: "+email);
        }
        return new User(funcionario.getEmail(), funcionario.getSenha(), getAuthorities(funcionario.getPermissoes()));
    }
    
    private List<GrantedAuthority> getAuthorities(List<Permissao> lista){
        List<GrantedAuthority> l = new ArrayList<>();
        for(Permissao p : lista){
            l.add(new SimpleGrantedAuthority("ROLE_"+p.getNome()));
        }
        return l;
    }
    
}
