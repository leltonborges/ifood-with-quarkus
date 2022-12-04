package org.project.ifood.pedido.kafka.incoming;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.project.ifood.pedido.dto.pedido.PedidoRealizadoDTO;
import org.project.ifood.pedido.mapper.PedidoMapper;
import org.project.ifood.pedido.model.Pedido;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PedidoIncoming {
    @Inject
    PedidoMapper pedidoMapper;

    @Incoming("pedidosKfk")
    @Transactional
    public CompletionStage<Void> process(Message<PedidoRealizadoDTO> message) {
        Pedido pedido = pedidoMapper.from(message.getPayload());
        return message.ack()
                      .thenRun(() -> pedido.persist()
                                           .subscribeAsCompletionStage()
                                           .obtrudeException(new PersistenceException("Error ao persisti pedido para" + pedido.client)));
    }
}
