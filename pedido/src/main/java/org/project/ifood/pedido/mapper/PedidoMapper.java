package org.project.ifood.pedido.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.project.ifood.pedido.dto.pedido.PedidoDTO;
import org.project.ifood.pedido.dto.pedido.PedidoRealizadoDTO;
import org.project.ifood.pedido.model.Pedido;

@Mapper(componentModel = "cdi", uses = { PratoMapper.class })
public interface PedidoMapper {
    @Mappings({
            @Mapping(source = "cliente", target = "client"),
            @Mapping(source = "pratos", target = "pratos")
    })
    Pedido from(PedidoRealizadoDTO dto);

    @Mappings({
            @Mapping(source = "pratos", target = "pratos"),
            @Mapping(source = "entregador", target = "entregador"),
            @Mapping(source = "client", target = "client"),
            @Mapping(source = "localizacaoEntregador", target = "localizacaoEntregador")
    })
    PedidoDTO fromDTO(Pedido pedido);
}
