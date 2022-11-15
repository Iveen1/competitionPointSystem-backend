package com.iveen.competitionPointSystem.service.impl;

import com.iveen.competitionPointSystem.domain.entity.Participant;
import com.iveen.competitionPointSystem.domain.entity.Point;
import com.iveen.competitionPointSystem.domain.mapper.ParticipantMapper;
import com.iveen.competitionPointSystem.domain.mapper.PointMapper;
import com.iveen.competitionPointSystem.domain.repository.ParticipantRepository;
import com.iveen.competitionPointSystem.domain.repository.PointRepository;
import com.iveen.competitionPointSystem.dto.PointDto;
import com.iveen.competitionPointSystem.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:34
 * @project competitionPointSystem
 */

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;
    private final ParticipantRepository participantRepository;
    private final PointMapper pointMapper;
    private final ParticipantMapper participantMapper;

    @Override
    public PointDto create(PointDto pointDto) {
        return pointMapper.toDto(pointRepository.save(pointMapper.toEntity(pointDto)));
    }

    @Override
    public PointDto findById(Long id) {
        return pointMapper.toDto(pointRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no point with id " + id)));
    }

    @Override
    public HashMap<String, Object> findByParticipantId(Long id) {
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no participant with id " + id));

        HashMap<String, Object> response = new HashMap<>();
        response.put("points", pointMapper.toDto(pointRepository.findPointsByParticipant(participant)));
        response.put("participant", participantMapper.toDto(participant));
        return response;
    }

    @Override
    public PointDto update(Long id, PointDto pointDto) {
        Point point = pointRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no point with id " + id));
        pointMapper.update(pointDto, point);
        return pointMapper.toDto(pointRepository.save(point));
    }

    @Override
    public void delete(Long id) {
        pointRepository.deleteById(id);
    }

    @Override
    public Page<PointDto> findAll(int page, int size) {
        Page<Point> entityPage = pointRepository.findAll(PageRequest.of(page, size));
        return new PageImpl<>(
                pointMapper.toDto(entityPage.toList()),
                PageRequest.of(page, size),
                entityPage.getTotalElements()
        );
    }
}
