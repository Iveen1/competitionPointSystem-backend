package com.iveen.competitionPointSystem.domain.mapper;

import com.iveen.competitionPointSystem.domain.entity.Point;
import com.iveen.competitionPointSystem.dto.PointDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointMapper {
    // to dto
    PointDto toDto(Point entity);
    List<PointDto> toDto (List<Point> entitites);

    // to entity
    @Mapping(target = "id", ignore = true)
    Point toEntity(PointDto dto);
    List<Point> toEntities(List<PointDto> dtos);

    @Mapping(target = "id", ignore = true)
    void update(PointDto dto, @MappingTarget Point point);
}
