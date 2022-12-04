package org.project.ifood.pedido.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

//@MongoEntity(collection = "restaurante", database = "pedido_ifood")
public class Restaurante extends ReactivePanacheMongoEntity {
    public Long idRestaurante;
    public String cnpj;
    public String nome;
}
