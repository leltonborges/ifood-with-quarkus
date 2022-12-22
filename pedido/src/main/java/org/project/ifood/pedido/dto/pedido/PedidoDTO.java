package org.project.ifood.pedido.dto.pedido;

import org.project.ifood.pedido.dto.prato.PratoPedidoDTO;
import org.project.ifood.pedido.model.Localizacao;

import java.util.List;

public class PedidoDTO {
    public String client;
    public List<PratoPedidoDTO> pratos;
    public String entregador;
    public Localizacao localizacaoEntregador;
}
