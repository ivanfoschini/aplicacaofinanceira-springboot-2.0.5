package br.ufscar.dc.latosensu.aplicacaofinanceira.validation;

import br.ufscar.dc.latosensu.aplicacaofinanceira.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationUtil {
    
    public void validate(BindingResult bindingResult) throws ValidationException {
        List<String> errors = new ArrayList();
        
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();            

            for (FieldError error: fieldErrors) {
                errors.add(error.getDefaultMessage());
            }

            throw new ValidationException(errors);
        }
    }
}
