package org.project.ifood.pedido.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.project.ifood.pedido.dto.pedido.PratoPedidoDTO;
import org.project.ifood.pedido.model.Prato;

import java.util.List;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PratoMapper {
    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(source = "id", target = "idPrato"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(target = "restaurante.id",ignore = true),
            @Mapping(source = "restaurante.id", target = "restaurante.idRestaurante"),
            @Mapping(source = "restaurante.nomeFantasia", target = "restaurante.nome"),
            @Mapping(source = "restaurante.cnpj", target = "restaurante.cnpj"),
            @Mapping(source = "preco", target = "preco"),
    })
    Prato from(PratoPedidoDTO dto);

    List<Prato> listFrom(List<PratoPedidoDTO> pedidoDTOS);
}
