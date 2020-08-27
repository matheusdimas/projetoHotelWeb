package br.edu.iff.projetoHotel.repository;

import br.edu.iff.projetoHotel.model.Cliente;
import br.edu.iff.projetoHotel.model.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    @Query("SELECT p FROM Pessoa p WHERE p.cpf = :cpf OR p.email = :email")
    public List<Pessoa> findByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);
}
