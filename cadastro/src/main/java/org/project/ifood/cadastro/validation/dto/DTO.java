package org.project.ifood.cadastro.validation.dto;

import javax.validation.ConstraintValidatorContext;

public interface DTO {
    default boolean isValid(ConstraintValidatorContext context){
        return true;
    }
}
