package br.edu.iff.projetoHotel.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reserva implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Data de registro é obrigatória.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dataHora;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Data de início da reserva é obrigatória.")
    @FutureOrPresent(message = "Data de inicio da reserva deve ser atual ou no futuro.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar inicio;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Data de término da reserva é obrigatória.")
    @Future(message = "Data de término da reserva deve ser atual ou no futuro.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar termino;
    
    @JsonManagedReference
    @ManyToMany
    @Size(min = 1, message = "Reserva deve ter no mínimo 1 quarto.")
    private List<Quarto> quartos = new ArrayList<>();
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Cliente obrigatório.")
    private Cliente cliente;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Funcionario obrigatório.")
    private Funcionario funcionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getTermino() {
        return termino;
    }

    public void setTermino(Calendar termino) {
        this.termino = termino;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public void setQuartos(List<Quarto> quartos) {
        this.quartos = quartos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
