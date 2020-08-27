package br.edu.iff.projetoHotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.Length;

@Entity
public class Cliente extends Pessoa{
    @Column(length = 200)
    @Length(max = 200, message = "Documentos deve ter no máximo 200 caracteres.")
    private String documentos;
    @JsonBackReference
    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas = new ArrayList<>();

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    
    
}
