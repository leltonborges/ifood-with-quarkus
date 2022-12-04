package org.project.ifood.pedido.kafka.incoming;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.project.ifood.pedido.dto.pedido.PedidoRealizadoDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PedidoIncoming {

    @Incoming("pedidosKfk")
    public CompletionStage<Void> process(Message<PedidoRealizadoDTO> message) {
        System.out.println(message.getPayload());
        return message.ack();
    }
}
