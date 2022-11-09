package com.iveen.competitionPointSystem.domain.repository;

import com.iveen.competitionPointSystem.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
