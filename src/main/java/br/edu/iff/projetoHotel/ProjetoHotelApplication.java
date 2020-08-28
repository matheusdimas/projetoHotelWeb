package br.edu.iff.projetoHotel;

import br.edu.iff.projetoHotel.model.Cliente;
import br.edu.iff.projetoHotel.model.Endereco;
import br.edu.iff.projetoHotel.model.Funcionario;
import br.edu.iff.projetoHotel.model.Hotel;
import br.edu.iff.projetoHotel.model.Quarto;
import br.edu.iff.projetoHotel.model.Reserva;
import br.edu.iff.projetoHotel.model.Telefone;
import br.edu.iff.projetoHotel.model.TipoQuartoEnum;
import br.edu.iff.projetoHotel.repository.ClienteRepository;
import br.edu.iff.projetoHotel.repository.FuncionarioRepository;
import br.edu.iff.projetoHotel.repository.HotelRepository;
import br.edu.iff.projetoHotel.repository.ReservaRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoHotelApplication implements CommandLineRunner{
    
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private FuncionarioRepository funcionarioRepo;
    @Autowired
    private HotelRepository hotelRepo;
    @Autowired
    private ReservaRepository reservaRepo;
    
    public static void main(String[] args) {
        SpringApplication.run(ProjetoHotelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Cliente
        Cliente c1 = new Cliente();
        c1.setNome("Matheus");
        c1.setCpf("765.620.220-02");
        c1.setEmail("matheus@gmail.com");
        
        Telefone t1 = new Telefone();
        t1.setNumero("(22)99999-9999");
        Telefone t2 = new Telefone();
        t2.setNumero("(22)88888-8888");
        
        c1.setTelefones(List.of(t1,t2));
        
        Endereco end = new Endereco();
        end.setRua("Rua das flores");
        end.setNumero(123);
        end.setBairro("Parque das flores");
        end.setCidade("Campos");
        end.setCep("28000-000");
        
        c1.setEndereco(end);
        clienteRepo.save(c1);
        
        //Funcionario
        Funcionario f1 = new Funcionario();
        f1.setNome("João");
        f1.setEmail("joao@gmail.com");
        f1.setCpf("325.291.890-05");
        f1.setEndereco(end);
        f1.setTelefones(List.of(t1,t2));
        f1.setSetor("Financeiro");
        f1.setSenha("12345678");
        
        funcionarioRepo.save(f1);
        
        //Hotel
        Hotel h1 = new Hotel();
        h1.setNome("Hotel Miraflores");
        h1.setCnpj("07.589.505/0001-82");
        h1.setEndereco(end);
        h1.setTelefones(List.of(t1,t2));
        
        Quarto q1 = new Quarto();
        q1.setNumero(101);
        q1.setTipo(TipoQuartoEnum.LUXO);
        q1.setQtdCamaCasal(1);
        q1.setQtdCamaSolteiro(1);
        Quarto q2 = new Quarto();
        q2.setNumero(102);
        q2.setTipo(TipoQuartoEnum.STANDARD);
        q2.setQtdCamaCasal(0);
        q2.setQtdCamaSolteiro(3);
        
        h1.setQuartos(List.of(q1,q2));
        
        hotelRepo.save(h1);
        
        // Reserva
        Reserva r1 = new Reserva();
        r1.setCliente(c1);
        r1.setFuncionario(f1);
        r1.setQuartos(List.of(q1, q2));
        
        r1.setDataHora(Calendar.getInstance());
        
        Calendar inicio = Calendar.getInstance();
        Calendar termino = Calendar.getInstance();
        inicio.set(2020, 10, 10);
        termino.set(2020, 10, 20);
        
        r1.setInico(inicio);
        r1.setTermino(termino);
        
        reservaRepo.save(r1);
    }


}
