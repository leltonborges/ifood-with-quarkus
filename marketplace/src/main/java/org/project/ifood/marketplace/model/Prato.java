package org.project.ifood.marketplace.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Prato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nome;
    public String descricao;

    @ManyToOne
    public Restaurante restaurante;
    public BigDecimal preco;
}
