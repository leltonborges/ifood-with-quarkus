package org.project.ifood.pedido.resource;

import io.smallrye.mutiny.Multi;
import org.project.ifood.pedido.dto.pedido.PedidoDTO;
import org.project.ifood.pedido.mapper.PedidoMapper;
import org.project.ifood.pedido.model.Pedido;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("pedidos")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class PedidoResource {
    @Inject
    PedidoMapper pedidoMapper;

    @GET
    public Multi<PedidoDTO> findAllPedidos() {
        return Pedido.<Pedido>streamAll().map(this.pedidoMapper::fromDTO);
    }

    @GET
    @Path("/all")
    public Multi<Pedido> findAll() {
        return Pedido.streamAll();
    }

}
