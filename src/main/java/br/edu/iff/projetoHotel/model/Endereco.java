package br.edu.iff.projetoHotel.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Embeddable
public class Endereco implements Serializable{
    private static final long serialVersionUID = 1L;
    @Column(length = 200, nullable = false)
    @NotBlank(message = "Rua obrigatória.")
    @Length(max = 200, message = "Rua deve ter no máximo 200 caracteres.")
    private String rua;
    @Column()
    @Digits(integer = 4, fraction = 0, message = "Número deve ser inteiro e ter até 4 digitos.")
    private int numero;
    @Column(length = 50, nullable = false)
    @NotBlank(message = "Bairro obrigatório.")
    @Length(max = 50, message = "Bairro deve ter no máximo 50 caracteres.")
    private String bairro;
    @Column(nullable = false, length = 50)
    @NotBlank(message = "Cidade obrigatória.")
    @Length(max = 50, message = "Cidade deve ter no máximo 50 caracteres.")
    private String cidade;
    @Column(length = 9)
    @Length(min = 9, max = 9, message = "CEP deve ter exatamente 9 caracteres (Ex: 99999-999).")
    private String cep;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.rua);
        hash = 47 * hash + this.numero;
        hash = 47 * hash + Objects.hashCode(this.bairro);
        hash = 47 * hash + Objects.hashCode(this.cidade);
        hash = 47 * hash + Objects.hashCode(this.cep);
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
        final Endereco other = (Endereco) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        return true;
    }
    
    
    
}
