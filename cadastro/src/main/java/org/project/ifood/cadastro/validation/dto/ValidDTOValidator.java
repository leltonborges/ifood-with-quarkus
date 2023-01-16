package org.project.ifood.cadastro.validation.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDTOValidator implements ConstraintValidator<ValidDTO, DTO> {
    @Override
    public boolean isValid(DTO dto, ConstraintValidatorContext context) {
        return dto.isValid(context);
    }

    @Override
    public void initialize(ValidDTO constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
