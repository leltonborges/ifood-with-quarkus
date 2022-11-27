package org.project.ifood.pedido.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

@MongoEntity(collection = "pedidos", database = "pedido")
public class Pedido extends PanacheMongoEntity {
    public String client;
    public List<Prato> pratoList;
    public Restaurante restaurante;
    public String entregador;
    public Localizacao localizacaoEntregador;
}
