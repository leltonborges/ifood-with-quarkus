package org.project.ifood.pedido.resource;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.mutiny.core.eventbus.EventBus;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.project.ifood.pedido.model.Localizacao;
import org.project.ifood.pedido.model.Pedido;
import org.project.ifood.pedido.service.PedidoService;
import org.project.ifood.pedido.utils.ParserJson;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/localizacao")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class LocalizacaoResource {

    PedidoService pedidoService;
    EventBus eventBus;
    Vertx vertx;

    @Inject
    public LocalizacaoResource(PedidoService pedidoService, EventBus eventBus, Vertx vertx) {
        this.pedidoService = pedidoService;
        this.eventBus = eventBus;
        this.vertx = vertx;
    }

    void startup(@Observes Router router) {
        router.route("/localizacoes*").handler(localizacaoHandler());
    }

    private SockJSHandler localizacaoHandler() {
        SockJSHandler handler = SockJSHandler.create(vertx);
        PermittedOptions permitted = new PermittedOptions();
        permitted.setAddress("novaLocalizacao");
        SockJSBridgeOptions bridgeOptions = new SockJSBridgeOptions().addOutboundPermitted(permitted);
        handler.bridge(bridgeOptions);
        return handler;
    }

    @POST
    @Path("/{idPedido}/localizacao")
    @Transactional
    public Uni<Pedido> nowLocalization(@PathParam("idPedido") String idPedido, @RequestBody Localizacao localizacao) {
        Uni<Pedido> pedido = this.pedidoService
                .addNewLocalization(new ObjectId(idPedido), localizacao);
        pedido.onItem()
              .invoke(p -> this.eventBus.send("novaLocalizacao", ParserJson.toJSON(p)))
              .onItem()
              .invoke(p -> eventBus.send("novaLocalizacao", p));
        return pedido;
    }

    @GET
    @Path("/all")
    public Multi<Pedido> findAll() {
        return Pedido.streamAll();
    }

}
