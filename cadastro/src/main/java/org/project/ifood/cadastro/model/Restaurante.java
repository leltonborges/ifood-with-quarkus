package org.project.ifood.cadastro.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_restaurante")
public class Restaurante extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String proprietario;
    public String cnpj;
    public String nome;

    @OneToOne(cascade = CascadeType.ALL)
    public Localizacao localizacao;

    @CreationTimestamp
    public Date dataCriacao;

    @UpdateTimestamp
    public Date dataAtualizacao;

}
