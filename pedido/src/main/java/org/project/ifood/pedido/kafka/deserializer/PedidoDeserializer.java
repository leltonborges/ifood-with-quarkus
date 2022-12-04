package org.project.ifood.pedido.kafka.deserializer;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.project.ifood.pedido.dto.pedido.PedidoRealizadoDTO;

public class PedidoDeserializer extends ObjectMapperDeserializer<PedidoRealizadoDTO> {
    public PedidoDeserializer() {
        super(PedidoRealizadoDTO.class);
    }
}
