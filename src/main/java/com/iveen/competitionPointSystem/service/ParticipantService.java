package com.iveen.competitionPointSystem.service;

import com.iveen.competitionPointSystem.dto.ParticipantDto;
import org.springframework.data.domain.Page;

public interface ParticipantService {
    ParticipantDto create(ParticipantDto participantDto);

    ParticipantDto findById(Long id);

    ParticipantDto update(Long id, ParticipantDto participantDto);

    void delete(Long id);

    Page<ParticipantDto> findAll(int page, int size);
}
