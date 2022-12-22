package org.project.ifood.pedido.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

import java.util.List;

@MongoEntity(collection = "pedido", database = "pedidos")
public class Pedido extends ReactivePanacheMongoEntity {

    public String client;
    public List<Prato> pratos;
    public String entregador;
    public Localizacao localizacaoEntregador;
}
