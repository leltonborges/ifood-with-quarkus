package org.project.ifood.pedido.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.project.ifood.pedido.dto.pedido.PedidoRealizadoDTO;
import org.project.ifood.pedido.model.Pedido;

@Mapper(componentModel = "cdi", uses = { PratoMapper.class })
public interface PedidoMapper {
    @Mappings({
            @Mapping(source = "cliente", target = "client"),
            @Mapping(source = "pratos", target = "pratoList")
    })
    Pedido from(PedidoRealizadoDTO dto);
}
