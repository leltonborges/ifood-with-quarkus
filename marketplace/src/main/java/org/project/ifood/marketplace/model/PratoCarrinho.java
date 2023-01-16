package org.project.ifood.marketplace.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_prato_cliente")
public class PratoCarrinho extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "prato")
    public Long prato;
    @Column(name = "cliente")
    public String cliente;
    @CreationTimestamp
    @Column(name = "createdat")
    public LocalDateTime createdAt;
    @Column(name = "finalizedAt")
    public LocalDateTime finallyAt;
    @Column(name = "finalized", columnDefinition = "boolean default false")
    public Boolean finalized = false;

    public PratoCarrinho() {
    }

    public PratoCarrinho(Long prato, String cliente) {
        this.prato = prato;
        this.cliente = cliente;
    }
}
