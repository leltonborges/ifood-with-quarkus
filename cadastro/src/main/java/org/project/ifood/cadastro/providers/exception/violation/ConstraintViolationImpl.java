package org.project.ifood.cadastro.providers.exception.violation;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ConstraintViolationImpl implements Serializable {
    private static final long serialVersionUID = -2333688930387754030L;

    @Schema(description = "Path do atributo, ex: dataInicio, pessoa.endereco.numero", required = false)
    private final String atributo;

    @Schema(description = "Mensagem descritiva do erro possivelmente associado ao path ou o Json", required = true)
    private final String mensagem;

    public ConstraintViolationImpl(ConstraintViolation<?> violation) {
        this.mensagem = violation.getMessage();
        this.atributo = getAtributo(violation.getPropertyPath().toString());
    }

    public ConstraintViolationImpl(String atributo, String mensagem) {
        this.atributo = atributo;
        this.mensagem = mensagem;
    }

    public static ConstraintViolationImpl of(ConstraintViolation<?> violation) {
        return new ConstraintViolationImpl(violation);
    }

    public static ConstraintViolationImpl of(String violation) {
        return new ConstraintViolationImpl(null, violation);
    }

    private String getAtributo(String text) {
        return Arrays.stream(text.split("\\.")).skip(2).collect(Collectors.joining("."));
    }

    public String getAtributo() {
        return atributo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
