package org.project.ifood.marketplace.resources;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.project.ifood.marketplace.dto.pedido.PedidoRealizadoDTO;
import org.project.ifood.marketplace.mapper.PratoCarrinhoMapper;
import org.project.ifood.marketplace.model.PratoCarrinho;
import org.project.ifood.marketplace.service.CarrionhoService;
import org.project.ifood.marketplace.service.PratoService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {
    private final String cliente = "abc";

    @Inject
    PratoService pratoService;
    @Inject
    CarrionhoService carrionhoService;
    @Inject
    PratoCarrinhoMapper pratoCarrinhoMapper;

    @Inject
    @Channel("pedidosKfk")
    Emitter<PedidoRealizadoDTO> pedidoRealizadoDTOEmitter;

    @GET
    public Multi<PratoCarrinho> findCarrinho() {
        return PratoCarrinho.stream("cliente", cliente);
    }

    @POST
    @Path("/prato/{idPrato}")
    public Uni<Response> addPrato(@PathParam("idPrato") Long idPrato) {
        return pratoService.findByPrato(idPrato).map(p -> new PratoCarrinho(p.id, cliente))
                           .chain(this.carrionhoService::save)
                           .onItem()
                           .transform(pc -> Response.status(Response.Status.CREATED)
                                                    .entity(pc)
                                                    .build());
    }

    @GET
    @Path("/details/{idPedido}")
    public Uni<Response> finalizarPedido(@PathParam("idPedido") Long idPedido) {
        return this.carrionhoService.findByIdPedidoAndClient(idPedido, cliente)
                                    .onItem()
                                    .transform(p -> Response.ok(p).build());
    }

    @Transactional
    @POST
    @Path("realizarPedido")
    public Uni<Response> finalizarPedidoClient() {
        return this.carrionhoService.findAllPedidoClientIsOpen(cliente)
                                    .onItem()
                                    .transformToUniAndMerge(this.carrionhoService::finish)
                                    .onItem()
                                    .transformToUniAndMerge(pc -> this.pratoService.findByPrato(pc.prato))
                                    .map(this.pratoCarrinhoMapper::fromPratoPedidoDTO)
                                    .collect()
                                    .asList()
                                    .map(pp -> new PedidoRealizadoDTO(pp, cliente))
                                    .invoke(this::sendMessageKafka)
                                    .onItem()
                                    .transform(it -> Response.accepted(it).build());
    }

    private void sendMessageKafka(PedidoRealizadoDTO obj) {
        this.pedidoRealizadoDTOEmitter.send(obj);
    }
}
