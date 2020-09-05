package br.edu.iff.projetoHotel.controller.apirest;

import br.edu.iff.projetoHotel.model.Reserva;
import br.edu.iff.projetoHotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/reservas")
public class ReservaController {
    @Autowired
    private ReservaService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "clienteId", defaultValue = "0", required = false) Long clienteId,
            @RequestParam(name = "funcionarioId", defaultValue = "0", required = false) Long funcionarioId,
            @RequestParam(name = "hotelId", defaultValue = "0", required = false) Long hotelId){
        
        return ResponseEntity.ok(service.findAll(page, size, clienteId, funcionarioId, hotelId));        
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@RequestBody Reserva reserva){
        reserva.setId(null);
        service.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Reserva reserva){
        reserva.setId(id);
        service.update(reserva);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
        
        
}
