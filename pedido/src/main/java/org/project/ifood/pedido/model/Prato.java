package org.project.ifood.pedido.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

import java.math.BigDecimal;

//@MongoEntity(collection = "prato", database = "pedido_ifood")
public class Prato extends ReactivePanacheMongoEntity {
    public Long idPrato;
    public String nome;
    public String descricao;
    //    public Decimal128 preco;
    public BigDecimal preco;
    public Restaurante restaurante;
}
