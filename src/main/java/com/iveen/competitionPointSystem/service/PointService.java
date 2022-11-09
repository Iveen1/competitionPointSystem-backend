package com.iveen.competitionPointSystem.service;

import com.iveen.competitionPointSystem.dto.PointDto;
import org.springframework.data.domain.Page;

public interface PointService {
    PointDto create(PointDto pointDto);

    PointDto update(Long id, PointDto pointDto);

    PointDto findById(Long id);

    void delete(Long id);

    Page<PointDto> findAll(int page, int size);
}
