package br.edu.iff.projetoHotel.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Embeddable
public class Telefone implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Column(length = 14, nullable = false)
    @NotBlank(message = "Número de telefone obrigatório.")
    @Length(min=13, max = 14, message = "Telefone deve ter 13 ou 14 caracteres (Ex: (99)9999-9999 ou (99)99999-9999")
    private String numero;
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.numero);
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
        final Telefone other = (Telefone) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }
    
    
    
}
