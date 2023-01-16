package org.project.ifood.marketplace.dto.restaurante;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.project.ifood.marketplace.dto.localizacao.LocalizacaoDTO;

public class AddRestauranteDTO {

    public Long id;
    @JsonProperty("nome")
    @JsonAlias({ "nomeFantasia" })
    public String nomeFantasia;
    public String cnpj;
    public String proprietario;
    public LocalizacaoDTO localizacao;
}
