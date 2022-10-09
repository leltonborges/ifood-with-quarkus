package org.project.ifood.cadastro.dto.prato;

import org.project.ifood.cadastro.validation.dto.DTO;
import org.project.ifood.cadastro.validation.dto.ValidDTO;

import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

@ValidDTO
public class AdicionarPratoDTO implements DTO {
    public String nome;
    public String descricao;
    public BigDecimal preco;

    @Override
    public boolean isValid(ConstraintValidatorContext context) {
        return DTO.super.isValid(context);
    }
}
