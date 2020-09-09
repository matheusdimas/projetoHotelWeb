package br.edu.iff.projetoHotel.repository;

import br.edu.iff.projetoHotel.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}
