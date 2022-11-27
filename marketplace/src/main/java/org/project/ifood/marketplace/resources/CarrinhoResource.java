package org.project.ifood.marketplace.resources;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.AssertSubscriber;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.project.ifood.marketplace.dto.pedido.PedidoRealizadoDTO;
import org.project.ifood.marketplace.dto.pedido.PratoPedidoDTO;
import org.project.ifood.marketplace.mapper.PratoCarrinhoMapper;
import org.project.ifood.marketplace.model.Prato;
import org.project.ifood.marketplace.model.PratoCarrinho;
import org.project.ifood.marketplace.service.CarrionhoService;
import org.project.ifood.marketplace.service.PratoService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    @POST
    @Path("/prato/{idPrato}")
    public Uni<Response> addPrato(@PathParam("idPrato") Long idPrato) {
        Prato prato = pratoService.findByPrato(idPrato).await().indefinitely();
        PratoCarrinho pratoCarrinho = new PratoCarrinho(prato.id, cliente);

        return carrionhoService.save(pratoCarrinho)
                               .onItem()
                               .transform(t -> Response.status(Response.Status.CREATED)
                                                       .entity(t)
                                                       .build());
    }

    @GET
    @Path("/details/{idPedido}")
    public Uni<Response> finalizarPedido(@PathParam("idPedido") Long idPedido) {

//        return PratoCarrinho.<PratoCarrinho>find("id = ?1 and cliente = ?2", idPedido, cliente)
        return this.carrionhoService.findByIdPedidoAndClient(idPedido, cliente)
                                    .onItem()
                                    .transform(carrionhoService::finish)
                                    .onItem()
                                    .transform(p -> Response.ok(p).build());
    }

    @Transactional
    @POST
    @Path("realizarPedido")
    public Uni<Response> finalizarPedidoClient() {
        List<PratoCarrinho> pratosCarrinho = this.carrionhoService.findAllPedidoClientIsOpen(cliente)
                                                                  .collect()
                                                                  .asList()
                                                                  .await()
                                                                  .indefinitely();
        List<PratoPedidoDTO> collect = pratosCarrinho.stream()
                                                     .map(pc -> this.pratoService.findByPrato(pc.prato)
                                                                                 .await()
                                                                                 .indefinitely())
                                                     .map(this.pratoCarrinhoMapper::fromPratoPedidoDTO)
                                                     .collect(Collectors.toList());
//        List<PratoPedidoDTO> pratosPedidoDTOs = getPratosPedidoDTOs(pratosCarrinho.map(this::mapPratoPedidoDTO));
        PedidoRealizadoDTO pedidoRealizadoDTO = new PedidoRealizadoDTO(collect, cliente);

        this.pedidoRealizadoDTOEmitter.send(pedidoRealizadoDTO);

//        finishPedido(pratosCarrinho);
        return Uni.createFrom().item(() -> Response.ok().build());
    }

    private List<PratoPedidoDTO> getPratosPedidoDTOs(Multi<PratoPedidoDTO> pratoPedidoDTOs) {
        return pratoPedidoDTOs.collect().asList().await().indefinitely();
    }

    private void finishPedido(Multi<PratoCarrinho> pratocarrinho) {
        pratocarrinho.map(this.carrionhoService::finish).subscribe();
    }

    private PratoPedidoDTO mapPratoPedidoDTO(PratoCarrinho p) {
        return this.pratoService.findByPrato(p.id)
                                .map(this.pratoCarrinhoMapper::fromPratoPedidoDTO)
                                .await().indefinitely();
    }
}
