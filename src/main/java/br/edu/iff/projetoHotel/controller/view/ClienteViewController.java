package br.edu.iff.projetoHotel.controller.view;

import br.edu.iff.projetoHotel.model.Cliente;
import br.edu.iff.projetoHotel.service.ClienteService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/clientes")
public class ClienteViewController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("clientes", service.findAll());
        return "clientes";
    }

    @GetMapping(path = "/cliente")
    public String cadastro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formCliente";
    }

    @PostMapping(path = "/cliente")
    public String salvar(@Valid @ModelAttribute Cliente cliente, BindingResult result,
            @RequestParam("file") MultipartFile file, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formCliente";
        }
        cliente.setId(null);
        try {
            service.save(cliente, file);
            model.addAttribute("msgSucesso", "Cliente cadastrado com sucesso.");
            model.addAttribute("cliente", new Cliente());
            return "formCliente";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("cliente", e.getMessage()));
            return "formCliente";
        }
    }
    
    @GetMapping(path = "/cliente/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cliente", service.findById(id));
        return "formCliente";
    }
    
    @PostMapping(path = "/cliente/{id}")
    public String atualizar(@Valid @ModelAttribute Cliente cliente, BindingResult result,
            @RequestParam("file") MultipartFile file, @PathVariable("id") Long id, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formCliente";
        }
        cliente.setId(id);
        try {
            service.update(cliente, file);
            model.addAttribute("msgSucesso", "Cliente cadastrado com sucesso.");
            model.addAttribute("cliente", cliente);
            return "formCliente";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("cliente", e.getMessage()));
            return "formCliente";
        }
    }
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/clientes";
    }
}
