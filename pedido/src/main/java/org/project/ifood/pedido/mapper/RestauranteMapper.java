package org.project.ifood.pedido.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.project.ifood.pedido.dto.restaurante.RestauranteDTO;
import org.project.ifood.pedido.model.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "id", target = "idRestaurante"),
            @Mapping(source = "cnpj", target = "cnpj"),
            @Mapping(source = "nomeFantasia", target = "nome")
    })
    Restaurante from(RestauranteDTO dto);

}
