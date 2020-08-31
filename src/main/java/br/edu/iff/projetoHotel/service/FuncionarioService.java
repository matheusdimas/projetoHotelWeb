package br.edu.iff.projetoHotel.service;

import br.edu.iff.projetoHotel.model.Funcionario;
import br.edu.iff.projetoHotel.model.Pessoa;
import br.edu.iff.projetoHotel.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repo;

    public List<Funcionario> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Funcionario> findAll() {
        return repo.findAll();
    }

    public Funcionario findById(Long id) {
        Optional<Funcionario> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Funcionario não encontrado.");
        }
        return result.get();
    }

    public Funcionario save(Funcionario f) {
        // verifica se cpf ou email estão cadastrados
        verificaCpfEmailCadastrado(f.getCpf(), f.getEmail());
        try {
            return repo.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o Funcionario.");
        }
    }

    public Funcionario update(Funcionario f, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        //Verifica de funcionario já existe
        Funcionario obj = findById(f.getId());
        //Verifica alteração da senha
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        try {
            f.setCpf(obj.getCpf());
            f.setEmail(obj.getEmail());
            return repo.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar o Funcionario.");
        }
    }
    
    public void delete(Long id){
        Funcionario obj = findById(id);
        verificaExclusaoClienteComReservas(obj);
        try{
        repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao excluir o Funcionario");
        }
        
    }

    private void verificaCpfEmailCadastrado(String cpf, String email) {
        List<Pessoa> result = repo.findByCpfOrEmail(cpf, email);
        if (!result.isEmpty()) {
            throw new RuntimeException("CPF ou EMAIL já cadastrados.");
        }
    }

    private void alterarSenha(Funcionario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        if (!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()) {
            if (!senhaAtual.equals(obj.getSenha())) {
                throw new RuntimeException("Senha atual está incorreta.");
            }
            if (!novaSenha.equals(confirmarNovaSenha)) {
                throw new RuntimeException("Nova Senha e Confirmar Nova Senha não conferem.");
            }
            obj.setSenha(novaSenha);
        }
    }

    private void verificaExclusaoClienteComReservas(Funcionario f) {
        if (!f.getReservas().isEmpty()) {
            throw new RuntimeException("Funcionario possui reservas. Não pode ser excluído.");
        }
    }
}
