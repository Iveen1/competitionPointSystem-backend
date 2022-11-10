package com.iveen.competitionPointSystem.domain.mapper;

import com.iveen.competitionPointSystem.domain.entity.Team;
import com.iveen.competitionPointSystem.dto.TeamDto;
import org.mapstruct.*;

import java.util.List;

@Named("TeamMapper")
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { ParticipantMapper.class })
public interface TeamMapper {
    // to dto
    @Named("teamToDto")
    @Mapping(target = "participants", ignore = true)
    TeamDto toDto(Team entity);
    @IterableMapping(qualifiedByName = "teamToDto")
    List<TeamDto> toDto (List<Team> entities);

    @Named("teamToDtoWithoutParticipants")
    @Mappings({
            @Mapping(target = "participants", expression = "java(null)")
    })
    TeamDto toDtoWithoutParticipants(Team team);
    @IterableMapping(qualifiedByName = "teamToDtoWithoutParticipants")
    List<TeamDto> toDtoWithoutParticipants(List<Team> teams);

    // to entity
    @Mapping(target = "id", ignore = true)
    Team toEntity(TeamDto dto);
    List<Team> toEntities(List<TeamDto> dtos);

    @Mapping(target = "id", ignore = true)
    void update(TeamDto dto, @MappingTarget Team team);
}
