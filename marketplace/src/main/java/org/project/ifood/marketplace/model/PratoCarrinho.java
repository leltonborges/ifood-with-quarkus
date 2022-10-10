package org.project.ifood.marketplace.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class PratoCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String usuario;

}
