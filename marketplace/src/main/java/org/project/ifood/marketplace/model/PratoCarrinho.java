package org.project.ifood.marketplace.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_prato_cliente")
public class PratoCarrinho extends PanacheEntityBase {
    @Id
    public Long id;
    public String usuario;

}
