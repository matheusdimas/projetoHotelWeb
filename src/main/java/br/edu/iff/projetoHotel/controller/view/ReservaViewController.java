package br.edu.iff.projetoHotel.controller.view;

import br.edu.iff.projetoHotel.model.Reserva;
import br.edu.iff.projetoHotel.service.ClienteService;
import br.edu.iff.projetoHotel.service.FuncionarioService;
import br.edu.iff.projetoHotel.service.HotelService;
import br.edu.iff.projetoHotel.service.ReservaService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/hoteis/{idHotel}/reservas")
public class ReservaViewController {

    @Autowired
    private ReservaService service;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String getAll(@PathVariable("idHotel") Long idHotel, Model model) {
        model.addAttribute("reservas", service.findAll(idHotel));
        model.addAttribute("idHotel", idHotel);
        return "reservas";
    }

    @GetMapping(path = "/reserva")
    public String cadastro(@PathVariable("idHotel") Long idHotel, Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("idHotel", idHotel);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("quartosHotel", hotelService.findById(idHotel).getQuartos());
        return "formReserva";
    }

    @PostMapping(path = "/reserva")
    public String save(@PathVariable("idHotel") Long idHotel,
            @Valid @ModelAttribute Reserva reserva,
            BindingResult result, Model model) {
        //Valores a serem retornados no model
        model.addAttribute("idHotel", idHotel);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("quartosHotel", hotelService.findById(idHotel).getQuartos());

        //Elimina erro de dataHora
        List<FieldError> list = new ArrayList<>();
        for (FieldError fe : result.getFieldErrors()) {
            if (!fe.getField().equals("dataHora")) {
                list.add(fe);
            }
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formReserva";
        }

        reserva.setId(null);
        try {
            service.save(reserva);
            model.addAttribute("msgSucesso", "Reserva cadastrada com sucesso.");
            model.addAttribute("reserva", new Reserva());
            return "formReserva";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("reserva", e.getMessage()));
            return "formReserva";
        }
    }
    
    @GetMapping(path = "/reserva/{id}")
    public String alterar(@PathVariable("idHotel") Long idHotel, @PathVariable("id") Long id, Model model) {
        model.addAttribute("reserva", service.findById(id));
        model.addAttribute("idHotel", idHotel);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("quartosHotel", hotelService.findById(idHotel).getQuartos());
        return "formReserva";
    }

    @PostMapping(path = "/reserva/{id}")
    public String update(@PathVariable("idHotel") Long idHotel,
            @PathVariable("id") Long id,
            @Valid @ModelAttribute Reserva reserva,
            BindingResult result, Model model) {
        //Valores a serem retornados no model
        model.addAttribute("idHotel", idHotel);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("quartosHotel", hotelService.findById(idHotel).getQuartos());

        //Elimina erro de dataHora
        List<FieldError> list = new ArrayList<>();
        for (FieldError fe : result.getFieldErrors()) {
            if (!fe.getField().equals("dataHora")) {
                list.add(fe);
            }
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formReserva";
        }

        reserva.setId(id);
        try {
            service.update(reserva);
            model.addAttribute("msgSucesso", "Reserva cadastrada com sucesso.");
            model.addAttribute("reserva", reserva);
            return "formReserva";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("reserva", e.getMessage()));
            return "formReserva";
        }
    }
    
    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("idHotel") Long idHotel, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/hoteis/"+idHotel+"/reservas";
    }
}
