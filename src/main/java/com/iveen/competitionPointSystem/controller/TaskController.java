package com.iveen.competitionPointSystem.controller;
import com.iveen.competitionPointSystem.dto.TaskDto;
import com.iveen.competitionPointSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 21:47
 * @project competitionPointSystem
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        TaskDto task = taskService.create(taskDto);
        return ResponseEntity.ok(Collections.singletonMap("response", "success created task with id " + task.getId()));
    }

    @GetMapping("")
    public Page<TaskDto> getTask(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        return taskService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        taskService.update(id, taskDto);
        return ResponseEntity.ok(Collections.singletonMap("response", "success update task with id " + id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("response", "success remove task with id " + id));
    }
}
