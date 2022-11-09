package com.iveen.competitionPointSystem.domain.repository;

import com.iveen.competitionPointSystem.domain.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
