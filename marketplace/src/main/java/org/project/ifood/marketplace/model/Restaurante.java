package org.project.ifood.marketplace.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "tb_restaurante")
public class Restaurante extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    @OneToOne(cascade = CascadeType.ALL)
    public Localizacao localizacao;
}
