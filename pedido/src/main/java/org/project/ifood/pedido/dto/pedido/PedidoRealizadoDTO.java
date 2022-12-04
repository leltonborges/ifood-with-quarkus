package org.project.ifood.pedido.dto.pedido;

import java.util.List;

public class PedidoRealizadoDTO {
    public List<PratoPedidoDTO> pratos;
    public String cliente;

    public PedidoRealizadoDTO() {
    }

    public PedidoRealizadoDTO(List<PratoPedidoDTO> pratos, String cliente) {
        this.pratos = pratos;
        this.cliente = cliente;
    }
}
