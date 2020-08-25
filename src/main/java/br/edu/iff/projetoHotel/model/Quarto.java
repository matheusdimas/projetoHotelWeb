package br.edu.iff.projetoHotel.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Quarto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private int numero;
    private TipoQuartoEnum tipo;
    private int qtdCamaSolteiro;
    private int qtdCamaCasal;
    
    private List<Reserva> reservas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoQuartoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuartoEnum tipo) {
        this.tipo = tipo;
    }

    public int getQtdCamaSolteiro() {
        return qtdCamaSolteiro;
    }

    public void setQtdCamaSolteiro(int qtdCamaSolteiro) {
        this.qtdCamaSolteiro = qtdCamaSolteiro;
    }

    public int getQtdCamaCasal() {
        return qtdCamaCasal;
    }

    public void setQtdCamaCasal(int qtdCamaCasal) {
        this.qtdCamaCasal = qtdCamaCasal;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Quarto other = (Quarto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
