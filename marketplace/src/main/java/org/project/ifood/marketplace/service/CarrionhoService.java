package org.project.ifood.marketplace.service;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.project.ifood.marketplace.model.PratoCarrinho;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;

@ApplicationScoped
public class CarrionhoService {
    @Inject
    protected EntityManager em;

    public Uni<PratoCarrinho> save(PratoCarrinho pratoCarrinho) {
        return Panache.withTransaction(pratoCarrinho::persist);
    }

    public Uni<PratoCarrinho> findByIdPedidoAndClient(Long idPedido, String client) {
        return PratoCarrinho.<PratoCarrinho>find("id = ?1 and cliente = ?2", idPedido, client)
                            .stream()
                            .toUni()
                            .onItem()
                            .ifNull()
                            .failWith(() -> new NotFoundException("Not found pedido: " + idPedido));

    }

    public Multi<PratoCarrinho> findAllPedidoClientIsOpen(String client) {
        return PratoCarrinho.<PratoCarrinho>stream("finalized = false and cliente = :client", Parameters.with("client", client));
    }

    public PratoCarrinho finish(PratoCarrinho pratoCarrinho) {
        pratoCarrinho.finallyAt = LocalDateTime.now();
        pratoCarrinho.finalized = true;
        save(pratoCarrinho).await().indefinitely();
        return pratoCarrinho;
    }
}
