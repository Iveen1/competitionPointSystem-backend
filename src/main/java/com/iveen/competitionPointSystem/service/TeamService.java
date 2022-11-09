package com.iveen.competitionPointSystem.service;
import com.iveen.competitionPointSystem.dto.TeamDto;
import com.iveen.competitionPointSystem.payload.request.TeamUpdateRequest;
import org.springframework.data.domain.Page;

public interface TeamService {
    TeamDto create(TeamDto teamDto);

    TeamDto update(Long id, TeamUpdateRequest teamUpdateRequest);

    TeamDto findById(Long id);

    void delete(Long id);

    Page<TeamDto> findAll(int page, int size);

    void addParticipant(Long participantId, Long teamId);

    void deleteParticipant(Long participantId, Long teamId);
}
