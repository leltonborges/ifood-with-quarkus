package org.project.ifood.pedido.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

//@MongoEntity(collection = "localizacao", database = "pedido_ifood")
public class Localizacao extends ReactivePanacheMongoEntity {
    public Double latitude;
    public Double longitude;
}
