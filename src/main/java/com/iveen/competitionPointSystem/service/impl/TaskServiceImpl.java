package com.iveen.competitionPointSystem.service.impl;

import com.iveen.competitionPointSystem.domain.entity.Task;
import com.iveen.competitionPointSystem.domain.mapper.TaskMapper;
import com.iveen.competitionPointSystem.domain.repository.TaskRepository;
import com.iveen.competitionPointSystem.dto.TaskDto;
import com.iveen.competitionPointSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:34
 * @project competitionPointSystem
 */

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDto create(TaskDto taskDto) {
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }
    @Override
    public TaskDto findById(Long id) {
        return taskMapper.toDto(taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no task with id " + id)));
    }

    @Override
    public TaskDto update(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no task with id " + id));
        taskMapper.update(taskDto, task);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Page<TaskDto> findAll(int page, int size) {
        Page<Task> entityPage = taskRepository.findAll(PageRequest.of(page, size));

        return new PageImpl<>(
                taskMapper.toDto(entityPage.toList()),
                PageRequest.of(page, size),
                entityPage.getTotalElements()
        );
    }
}
