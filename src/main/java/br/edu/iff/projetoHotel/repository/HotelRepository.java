
package br.edu.iff.projetoHotel.repository;

import br.edu.iff.projetoHotel.model.Hotel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
    public Optional<Hotel> findByCnpj(String cnpj);
}
