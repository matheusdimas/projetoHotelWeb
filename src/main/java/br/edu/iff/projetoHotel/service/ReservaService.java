package br.edu.iff.projetoHotel.service;

import br.edu.iff.projetoHotel.model.Quarto;
import br.edu.iff.projetoHotel.model.Reserva;
import br.edu.iff.projetoHotel.repository.ReservaRepository;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repo;

    public List<Reserva> findAll() {
        return repo.findAll();
    }

    public List<Reserva> findAll(int page, int size, Long clienteId, Long funcionarioId, Long hotelId) {
        Pageable p = PageRequest.of(page, size);
        if (clienteId != 0 && funcionarioId != 0) {
            return repo.findByClienteIdAndFuncionarioId(clienteId, funcionarioId, p);
        } else if (clienteId != 0) {
            return repo.findByClienteId(clienteId, p);
        } else if (funcionarioId != 0) {
            return repo.findByFuncionarioId(funcionarioId, p);
        }

        if (hotelId != 0) {
            return repo.findByReservasByHotel(hotelId);
        }

        return repo.findAll(p).toList();
    }

    public Reserva findById(Long id) {
        Optional<Reserva> obj = repo.findById(id);
        if (obj.isEmpty()) {
            throw new RuntimeException("Reserva não encontrada.");
        }
        return obj.get();
    }

    public Reserva save(Reserva r) {
        //Verificar se a data de inicio é anterior a data de termino
        verificarDataInicioETermino(r);
        //Verificar se quartos já estão reservados.
        verificarQuartosReservados(r);
        try {
            r.setDataHora(Calendar.getInstance());
            return repo.save(r);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar a Reserva.");
        }
    }

    public Reserva update(Reserva r) {
        //verificar se já existe
        Reserva obj = findById(r.getId());
        //Verificar se a data de inicio é anterior a data de termino
        verificarDataInicioETermino(r);
        //Verificar se quartos já estão reservados.
        verificarQuartosReservados(r);

        try {
            r.setDataHora(Calendar.getInstance());
            return repo.save(r);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar a Reserva");
        }
    }
    
    public void delete(Long id){
        Reserva obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar a Reserva.");
        }
    }

    private void verificarQuartosReservados(Reserva r) {
        List<Reserva> reservas = repo.findReservasEntreDatas(r.getInicio(), r.getTermino());
        for (Reserva reserva : reservas) {
            for (Quarto q : reserva.getQuartos()) {
                if ((r.getQuartos().contains(q)) && (!Objects.equals(r, reserva))) {
                    throw new RuntimeException("Quartos já reservados para o período.");
                }
            }
        }
    }

    private void verificarDataInicioETermino(Reserva r) {
        if (r.getInicio().compareTo(r.getTermino()) >= 0) {
            throw new RuntimeException("Data de início deve ser anterior a data de término.");
        }
    }

}
