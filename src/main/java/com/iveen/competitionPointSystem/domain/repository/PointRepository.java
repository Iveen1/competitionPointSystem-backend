package com.iveen.competitionPointSystem.domain.repository;

import com.iveen.competitionPointSystem.domain.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
