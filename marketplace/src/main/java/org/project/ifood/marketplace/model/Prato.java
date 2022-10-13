package org.project.ifood.marketplace.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_prato")
public class Prato extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nome;
    public String descricao;

    @ManyToOne
    public Restaurante restaurante;
    public BigDecimal preco;
}
