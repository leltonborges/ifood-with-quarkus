package org.project.ifood.marketplace.service;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.project.ifood.marketplace.dto.pedido.PedidoRealizadoDTO;
import org.project.ifood.marketplace.model.PratoCarrinho;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;

@ApplicationScoped
public class CarrionhoService {

    public Uni<PratoCarrinho> save(PratoCarrinho pratoCarrinho) {
        return Panache.<PratoCarrinho>withTransaction(pratoCarrinho::persist)
                      .onItem()
                      .ifNull()
                      .failWith(() -> new PersistenceException("Persistence fail entity: " + pratoCarrinho));
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
        return PratoCarrinho.<PratoCarrinho>stream(
                                    "finalized = false and cliente = ?1",
                                    client)
                            .onCompletion()
                            .ifEmpty()
                            .failWith(() -> new NotFoundException("Not found customer request: " + client));
    }


    //    @ActivateRequestContext
    public Uni<PratoCarrinho> finish(PratoCarrinho pratoCarrinho) {
        pratoCarrinho.finallyAt = LocalDateTime.now();
        pratoCarrinho.finalized = true;
        return save(pratoCarrinho);
    }

    public void finish(PedidoRealizadoDTO pedidoRealizadoDTO) {

    }
}
