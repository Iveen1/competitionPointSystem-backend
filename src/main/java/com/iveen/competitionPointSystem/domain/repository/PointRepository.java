package com.iveen.competitionPointSystem.domain.repository;

import com.iveen.competitionPointSystem.domain.entity.Participant;
import com.iveen.competitionPointSystem.domain.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findPointsByParticipant(Participant participant);
}
