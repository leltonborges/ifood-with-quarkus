package org.project.ifood.pedido.outgoing;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

//@Provider
public class LocalizacaoSocket {

    @Inject
    Vertx vertx;

    void startup(@Observes Router router) {
        router.route("/localizacoes*").handler(localizacaoHandler());
    }

    private Handler<RoutingContext> localizacaoHandler() {
        SockJSHandler handler = SockJSHandler.create(vertx);
        PermittedOptions permittedOptions = new PermittedOptions();
        permittedOptions.setAddress("novaLocalizacao");
        SockJSBridgeOptions bridgeOptions = new SockJSBridgeOptions().addOutboundPermitted(permittedOptions);

        handler.bridge(bridgeOptions);
        return handler;
    }
}
