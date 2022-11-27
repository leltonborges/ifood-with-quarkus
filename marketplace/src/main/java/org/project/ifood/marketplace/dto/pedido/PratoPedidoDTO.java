package org.project.ifood.marketplace.dto.pedido;

import org.project.ifood.marketplace.dto.restaurante.RestauranteDTO;

import java.math.BigDecimal;

public class PratoPedidoDTO {
    public String nome;

    public String descricao;

    public BigDecimal preco;

    public RestauranteDTO restaurante;
}
