package com.iveen.competitionPointSystem.domain.repository;

import com.iveen.competitionPointSystem.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
