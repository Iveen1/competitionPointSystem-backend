package com.iveen.competitionPointSystem.domain.mapper;
import com.iveen.competitionPointSystem.domain.entity.Point;
import com.iveen.competitionPointSystem.dto.PointDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { ParticipantMapper.class })
public interface PointMapper {
    // to dto
    @Named("pointToDto")
    @Mapping(target = "participant", qualifiedByName = {"participantToDto"})
    PointDto toDto(Point entity);

    @Named("pointToDtoWithoutParticipant")
    @Mapping(target = "participant", ignore = true)
    PointDto toDtoWithoutParticipant(Point entity);

    @IterableMapping(qualifiedByName = "pointToDtoWithoutParticipant")
    List<PointDto> toDto(List<Point> entities);

    // to entity
    @Mapping(target = "id", ignore = true)
    Point toEntity(PointDto dto);
    List<Point> toEntities(List<PointDto> dtos);

    @Mapping(target = "id", ignore = true)
    void update(PointDto dto, @MappingTarget Point point);
}
