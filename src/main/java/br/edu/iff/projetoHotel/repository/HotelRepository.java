
package br.edu.iff.projetoHotel.repository;

import br.edu.iff.projetoHotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
    
}
