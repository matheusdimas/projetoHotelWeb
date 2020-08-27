package br.edu.iff.projetoHotel.annotation;

import br.edu.iff.projetoHotel.model.Quarto;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumeroQuartosValidator implements ConstraintValidator<NumeroQuartosValidation, List<Quarto>>{

    @Override
    public boolean isValid(List<Quarto> value, ConstraintValidatorContext context) {
        if(value==null) return false;
        for(int i = 0; i< value.size(); i++){
            for(int j = i+1; j < value.size(); j++){
                if(value.get(i).getNumero()==value.get(j).getNumero()){
                    return false;
                }
            }
        }
        return true;
    }
    
}
