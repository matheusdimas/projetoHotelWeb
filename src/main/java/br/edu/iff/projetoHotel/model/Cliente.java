package br.edu.iff.projetoHotel.model;

import java.util.List;

public class Cliente extends Pessoa{
    private String documentos;
    
    private List<Reserva> reservas;

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
