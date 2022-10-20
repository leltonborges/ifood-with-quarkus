package org.project.ifood.marketplace.service;

import io.smallrye.mutiny.Multi;
import org.project.ifood.marketplace.model.Prato;
import org.project.ifood.marketplace.model.Restaurante;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PratoService {

    public Multi<Prato> getPratoAll(){
        return Prato.<Prato>streamAll();
    }
    public Multi<Prato> getPratoByRestaurante(Restaurante restaurante) {
        return Prato.<Prato>stream("restaurante", restaurante);
    }
}
