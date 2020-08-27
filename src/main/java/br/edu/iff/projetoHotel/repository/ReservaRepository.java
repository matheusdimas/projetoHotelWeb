package br.edu.iff.projetoHotel.repository;

import br.edu.iff.projetoHotel.model.Reserva;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    @Query("SELECT DISTINCT(r) FROM Hotel h JOIN h.quartos q JOIN q.reservas r WHERE h.id = :hotelId")
    public List<Reserva> findByReservasByHotel(@Param("hotelId") Long hotelId);
    
    public List<Reserva> findByFuncionarioId(Long funcionarioId, Pageable page);
    
    public List<Reserva> findByClienteId(Long funcionarioId, Pageable page);
    
    public List<Reserva> findByClienteIdAndFuncionarioId(Long ClienteId, Long funcionarioId, Pageable page);
    
    @Query("SELECT DISTINCT(r) FROM Reserva r WHERE (r.inicio BETWEEN :inicio AND :ternimo) OR (r.termino BETWEEN :inicio AND :ternimo)")
    public List<Reserva> findReservasEntreDatas(Calendar inicio, Calendar termino);
}
