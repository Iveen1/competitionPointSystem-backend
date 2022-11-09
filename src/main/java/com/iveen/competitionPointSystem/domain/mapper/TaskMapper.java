package com.iveen.competitionPointSystem.domain.mapper;

import com.iveen.competitionPointSystem.domain.entity.Task;
import com.iveen.competitionPointSystem.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    // to dto
    TaskDto toDto(Task entity);
    List<TaskDto> toDto (List<Task> entitites);

    // to entity
    @Mapping(target = "id", ignore = true)
    Task toEntity(TaskDto dto);
    List<Task> toEntities(List<TaskDto> dtos);

    @Mapping(target = "id", ignore = true)
    void update(TaskDto dto, @MappingTarget Task task);
}
