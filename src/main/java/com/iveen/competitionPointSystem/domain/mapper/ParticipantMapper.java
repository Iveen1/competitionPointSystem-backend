package com.iveen.competitionPointSystem.domain.mapper;

import com.iveen.competitionPointSystem.domain.entity.Participant;
import com.iveen.competitionPointSystem.domain.entity.Team;
import com.iveen.competitionPointSystem.dto.ParticipantDto;
import com.iveen.competitionPointSystem.dto.TeamDto;
import org.mapstruct.*;

import java.util.List;

@Named("ParticipantMapper")
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { TeamMapper.class })
public interface ParticipantMapper {
    // to dto
    @Named("toDto")
    @Mapping(target = "team", qualifiedByName = {"TeamMapper", "toDtoWithoutParticipants"})
    ParticipantDto toDto(Participant entity);
    @IterableMapping(qualifiedByName = "toDto")
    List<ParticipantDto> toDto (List<Participant> entities);

    @Named("toDtoWithoutTeam")
    @Mappings({
            @Mapping(target = "name", expression = "java(null)")
    })
    TeamDto toDtoWithoutTeam(Team team);

    // to entity
    @Mapping(target = "id", ignore = true)
    Participant toEntity(ParticipantDto dto);
    List<Participant> toEntities(List<ParticipantDto> dtos);

    @Mapping(target = "id", ignore = true)
    void update(ParticipantDto dto, @MappingTarget Participant participant);
}