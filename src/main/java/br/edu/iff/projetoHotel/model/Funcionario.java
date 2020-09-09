package br.edu.iff.projetoHotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.constraints.Length;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class Funcionario extends Pessoa{
    @Column(nullable = false, length = 50)
    @NotBlank(message = "Setor obrigatório.")
    @Length(max = 50, message = "Setor deve ter no máximo 50 caracteres.")
    private String setor;
    @Column(nullable = false)
    @NotBlank(message = "Senha obrigatória.")
    @Length(min = 8, message = "Senha deve ter no mínimo 8 caracteres.")
    private String senha;
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Reserva> reservas = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Funcionário deve ter no mínimo 1 permissão.")
    private List<Permissao> permissoes = new ArrayList<>();

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    
    
    
}
