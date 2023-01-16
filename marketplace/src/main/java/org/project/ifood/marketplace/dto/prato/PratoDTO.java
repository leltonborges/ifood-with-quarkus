package org.project.ifood.marketplace.dto.prato;

import org.project.ifood.marketplace.model.Restaurante;

import java.math.BigDecimal;

public class PratoDTO {
    public PratoDTO() {
    }

    public PratoDTO(Long id, String nome, String descricao, Restaurante restaurante, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.restaurante = restaurante;
        this.preco = preco;
    }

    public Long id;

    public String nome;
    public String descricao;

    public Restaurante restaurante;
    public BigDecimal preco;
}
