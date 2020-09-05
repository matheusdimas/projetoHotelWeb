package br.edu.iff.projetoHotel.controller.view;

import br.edu.iff.projetoHotel.model.Hotel;
import br.edu.iff.projetoHotel.model.TipoQuartoEnum;
import br.edu.iff.projetoHotel.service.HotelService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/hoteis")
public class HotelViewController {

    @Autowired
    private HotelService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("hoteis", service.findAll());
        return "hoteis";
    }

    @GetMapping(path = "/hotel")
    public String cadastro(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("tiposQuarto", TipoQuartoEnum.values());
        return "formHotel";
    }

    @PostMapping(path = "/hotel")
    public String save(@Valid @ModelAttribute Hotel hotel, BindingResult result, Model model) {
        //valores de retorno padão
        model.addAttribute("tiposQuarto", TipoQuartoEnum.values());
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formHotel";
        }
        hotel.setId(null);
        try {
            service.save(hotel);
            model.addAttribute("msgSucesso", "Hotel cadastrado com sucesso.");
            model.addAttribute("hotel", new Hotel());
            return "formHotel";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Hotel", e.getMessage()));
            return "formHotel";
        }
    }
    
    @GetMapping(path = "/hotel/{id}")
    public String alterar(@PathVariable("id") Long id,Model model) {
        model.addAttribute("hotel", service.findById(id));
        model.addAttribute("tiposQuarto", TipoQuartoEnum.values());
        return "formHotel";
    }
    
    @PostMapping(path = "/hotel/{id}")
    public String update(@Valid @ModelAttribute Hotel hotel, BindingResult result, @PathVariable("id") Long id, Model model) {
        //valores de retorno padão
        model.addAttribute("tiposQuarto", TipoQuartoEnum.values());
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formHotel";
        }
        hotel.setId(id);
        try {
            service.update(hotel);
            model.addAttribute("msgSucesso", "Hotel atualizado com sucesso.");
            model.addAttribute("hotel", hotel);
            return "formHotel";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Hotel", e.getMessage()));
            return "formHotel";
        }
    }
    
    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/hoteis";
    }

}
