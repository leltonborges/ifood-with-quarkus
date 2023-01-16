package org.project.ifood.cadastro.dto.restaurante;

import org.project.ifood.cadastro.dto.localizacao.LocalizacaoDTO;

public class RestauranteDTO {

    public Long id;
    public String proprietario;
    public String cnpj;
    public String nomeFantasia;
    public LocalizacaoDTO localizacao;
    public String dataCriacao;
}
