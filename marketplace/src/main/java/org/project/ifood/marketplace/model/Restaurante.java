package org.project.ifood.marketplace.model;

import javax.persistence.*;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    @OneToOne(cascade = CascadeType.ALL)
    public Localizacao localizacao;
}
