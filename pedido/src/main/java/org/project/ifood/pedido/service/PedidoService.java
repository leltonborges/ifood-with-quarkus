package org.project.ifood.pedido.service;

import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.project.ifood.pedido.mapper.PratoMapper;
import org.project.ifood.pedido.model.Localizacao;
import org.project.ifood.pedido.model.Pedido;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoService {

    PratoMapper pratoMapper;

    @Inject
    public PedidoService(PratoMapper pratoMapper) {
        this.pratoMapper = pratoMapper;
    }

    public Uni<Pedido> addNewLocalization(ObjectId idPedido, Localizacao localizacao) {
        return Pedido.<Pedido>findById(idPedido)
                     .onItem()
                     .ifNull()
                     .failWith(new NotFoundException("Pedido não encontrado: " + idPedido))
                     .map(pedido -> this.pratoMapper.addNewLocalizacao(localizacao, pedido))
                     .chain(this::saveOrUpdate);
    }

    private Uni<Pedido> saveOrUpdate(Pedido pedido) {
        return pedido.persistOrUpdate();
    }
}
