package br.edu.iff.projetoHotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class MyViewControllerAdvice {
    
    @ExceptionHandler(Exception.class)
    public String errorException(Exception e, Model model){
        model.addAttribute("msgErros", e.getMessage());
        return "error";
    }
}
