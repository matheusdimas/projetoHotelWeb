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
public class ProjetoHotelApplication implements CommandLineRunner {
    
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
        c1.setCpf("589.173.490-72");
        c1.setEmail("matheus@gmail.com");
        
        Telefone t1 = new Telefone();
        t1.setNumero("(22)99999-9999");
        Telefone t2 = new Telefone();
        t2.setNumero("(22)88888-8888");
        
        c1.setTelefones(List.of(t1,t2));
        
        Endereco end = new Endereco();
        end.setRua("Rua das flores");
        end.setNumero(123);
        end.setBairro("Parque da flores");
        end.setCidade("Campos");
        end.setCep("28000-000");
        
        c1.setEndereco(end);
        
        clienteRepo.save(c1);
        
        // Funcionario
        Funcionario f1 = new Funcionario();
        f1.setNome("João");
        f1.setEmail("joao@gmail.com");
        f1.setCpf("127.340.690-75");
        f1.setSetor("Financeiro");
        f1.setSenha("12345678");
        f1.setTelefones(List.of(t1,t2));
        f1.setEndereco(end);
        
        funcionarioRepo.save(f1);
        
        //Hotel
        Hotel h1 = new Hotel();
        h1.setNome("Hotel Miraflores");
        h1.setCnpj("93.722.399/0001-03");
        h1.setTelefones(List.of(t1,t2));
        h1.setEndereco(end);
        
        Quarto q1 = new Quarto();
        q1.setNumero(101);
        q1.setTipo(TipoQuartoEnum.LUXO);
        q1.setQtdCamaCasal(1);
        q1.setQtdCamaSolteiro(2);
        Quarto q2 = new Quarto();
        q2.setNumero(102);
        q2.setTipo(TipoQuartoEnum.STANDARD);
        q2.setQtdCamaCasal(0);
        q2.setQtdCamaSolteiro(3);
        
        h1.setQuartos(List.of(q1,q2));
        
        hotelRepo.save(h1);
        
        // Reserva
        Reserva r1 = new Reserva();
        r1.setDataHora(Calendar.getInstance());
        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();
        data1.set(2020, 10, 10);
        data2.set(2020, 10, 20);
        r1.setInico(data1);
        r1.setTermino(data2);
        r1.setCliente(c1);
        r1.setFuncionario(f1);
        r1.setQuartos(List.of(q1, q2));
        
        reservaRepo.save(r1);
    }

}
