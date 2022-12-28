package org.project.ifood.pedido.dto.prato;

import org.project.ifood.pedido.dto.restaurante.RestauranteDTO;

import java.math.BigDecimal;

public class PratoPedidoDTO {
    public String nome;

    public String descricao;

    public BigDecimal preco;

    public RestauranteDTO restaurante;
}
