package org.project.ifood.pedido.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class IgnoreUnmappedMapperConfig {
}
