package br.edu.iff.projetoHotel.annotation;

import br.edu.iff.projetoHotel.model.Quarto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuartoCamasValidator implements ConstraintValidator<QuartoCamasValidation, Quarto>{

    @Override
    public boolean isValid(Quarto value, ConstraintValidatorContext context) {
        if(value==null) return false;
        int soma = value.getQtdCamaCasal()*2 + value.getQtdCamaSolteiro()*1;
        return (soma<=4);
    }
    
}
