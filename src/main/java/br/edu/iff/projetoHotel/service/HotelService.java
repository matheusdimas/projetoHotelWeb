package br.edu.iff.projetoHotel.service;

import br.edu.iff.projetoHotel.exception.NotFoundException;
import br.edu.iff.projetoHotel.model.Hotel;
import br.edu.iff.projetoHotel.model.Quarto;
import br.edu.iff.projetoHotel.repository.HotelRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repo;

    public List<Hotel> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Hotel> findAll() {
        return repo.findAll();
    }

    public Hotel findById(Long id) {
        Optional<Hotel> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Hotel não encontrado.");
        }
        return result.get();
    }

    public Hotel save(Hotel h) {
        verificaCnpjCadastrado(h.getCnpj());
        try {
            return repo.save(h);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o hotel.");
        }
    }
    
    public Hotel update(Hotel h){
        Hotel obj = findById(h.getId());
        
        List<Quarto> quartosAtuais = obj.getQuartos();
        quartosAtuais.removeAll(h.getQuartos());
        verificaExclusaoQuartosComReservas(quartosAtuais);
        
        try{
            h.setCnpj(obj.getCnpj());
            return repo.save(h);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar o Hotel.");
        }
    }
    
    public void delete(Long id){
        Hotel obj = findById(id);
        verificaExclusaoQuartosComReservas(obj.getQuartos());
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar o Hotel.");
        }
    }
    
    private void verificaExclusaoQuartosComReservas(List<Quarto> quartos){
        for(Quarto q : quartos){
            if(!q.getReservas().isEmpty()){
                throw new RuntimeException("Não é possível excluir quartos com reservas.");
            }
        }
    }   
    
    private void verificaCnpjCadastrado(String cnpj) {
        Optional<Hotel> result = repo.findByCnpj(cnpj);
        if (!result.isEmpty()) {
            throw new RuntimeException("CNPJ já cadastrado.");
        }
    }
}
