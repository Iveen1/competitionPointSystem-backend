package com.iveen.competitionPointSystem.service;

import com.iveen.competitionPointSystem.dto.TaskDto;
import org.springframework.data.domain.Page;

public interface TaskService {
    TaskDto create(TaskDto taskDto);

    TaskDto update(Long id, TaskDto taskDto);

    TaskDto findById(Long id);

    void delete(Long id);

    Page<TaskDto> findAll(int page, int size);
}
