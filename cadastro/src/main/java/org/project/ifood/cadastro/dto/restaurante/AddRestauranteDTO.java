package org.project.ifood.cadastro.dto.restaurante;

import org.project.ifood.cadastro.dto.localizacao.LocalizacaoDTO;
import org.project.ifood.cadastro.model.Restaurante;
import org.project.ifood.cadastro.validation.dto.DTO;
import org.project.ifood.cadastro.validation.dto.ValidDTO;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValidDTO
public class AddRestauranteDTO implements DTO {
    @Size(min = 3, max = 150)
    public String nomeFantasia;
    @NotBlank
    @Pattern(regexp = "(^\\d{2}\\x2E\\d{3}\\x2E\\d{3}\\x2F\\d{4}\\x2D\\d{2}$)")
    public String cnpj;
    @NotBlank
    public String proprietario;
    public LocalizacaoDTO localizacao;

    @Override
    public boolean isValid(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if(Restaurante.find("cnpj", cnpj).count() > 0){
            context.buildConstraintViolationWithTemplate("Exists CNPJ")
                    .addPropertyNode("cpnj")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
