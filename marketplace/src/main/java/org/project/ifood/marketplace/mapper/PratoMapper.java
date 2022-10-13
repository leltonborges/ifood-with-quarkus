package org.project.ifood.marketplace.mapper;

import org.mapstruct.Mapper;
import org.project.ifood.marketplace.dto.prato.PratoDTO;
import org.project.ifood.marketplace.model.Prato;

@Mapper(componentModel = "cdi")
public interface PratoMapper {
    PratoDTO toDTO(Prato prato);
}
