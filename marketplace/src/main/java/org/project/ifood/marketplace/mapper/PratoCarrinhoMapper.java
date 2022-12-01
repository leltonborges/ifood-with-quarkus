package org.project.ifood.marketplace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.project.ifood.marketplace.dto.pedido.PratoPedidoDTO;
import org.project.ifood.marketplace.model.Prato;
import org.project.ifood.marketplace.model.PratoCarrinho;

@Mapper(componentModel = "cdi")
public interface PratoCarrinhoMapper {

    @Mappings({
            @Mapping(source = "id", target = "prato")
    })
    PratoCarrinho to(Prato prato);

    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "preco", target = "preco"),
            @Mapping(source = "restaurante.nome", target = "restaurante.nomeFantasia")
    })
    PratoPedidoDTO fromPratoPedidoDTO(Prato prato);
}
