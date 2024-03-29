package org.project.ifood.marketplace.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "tb_restaurante")
public class Restaurante extends PanacheEntityBase {

    @Id
    public Long id;
    public String nome;
    @OneToOne(cascade = CascadeType.ALL)
    public Localizacao localizacao;
    public String cnpj;

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localizacao=" + localizacao +
                '}';
    }
}
