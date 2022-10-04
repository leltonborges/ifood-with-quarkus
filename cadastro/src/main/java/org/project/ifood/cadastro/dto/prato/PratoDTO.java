package org.project.ifood.cadastro.dto.prato;

import org.project.ifood.cadastro.dto.restaurante.RestauranteDTO;

import java.math.BigDecimal;

public class PratoDTO {
    public Long id;
    public String nome;
    public String descricao;
    public RestauranteDTO restaurante;
    public BigDecimal preco;
}
