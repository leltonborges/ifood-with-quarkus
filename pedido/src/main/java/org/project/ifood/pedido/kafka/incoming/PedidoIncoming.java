package org.project.ifood.pedido.kafka.incoming;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.project.ifood.pedido.dto.pedido.PedidoRealizadoDTO;
import org.project.ifood.pedido.elasticsearch.ESPedidoService;
import org.project.ifood.pedido.handle.exceptions.PersistenceException;
import org.project.ifood.pedido.mapper.PedidoMapper;
import org.project.ifood.pedido.model.Pedido;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PedidoIncoming {
    @Inject
    PedidoMapper pedidoMapper;

    @Inject
    ESPedidoService esPedidoService;

    @Incoming("pedidosKfk")
    @Transactional
    public CompletionStage<Void> process(Message<PedidoRealizadoDTO> message) {
        Pedido pedido = pedidoMapper.from(message.getPayload());
        return message.ack()
                      .thenRun(() -> this.esPedidoService.index(message.getPayload()))
                      .thenRun(() -> pedido.persist()
                                           .subscribeAsCompletionStage()
                                           .obtrudeException(new PersistenceException("Error ao persisti pedido para" + pedido.client)));
    }
}
