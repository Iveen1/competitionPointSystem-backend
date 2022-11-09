package com.iveen.competitionPointSystem.service;
import com.iveen.competitionPointSystem.dto.TeamDto;
import org.springframework.data.domain.Page;

import java.sql.SQLException;

public interface TeamService {
    TeamDto create(TeamDto teamDto);

    TeamDto update(Long id, TeamDto teamDto);

    TeamDto findById(Long id);

    void delete(Long id);

    Page<TeamDto> findAll(int page, int size);

    void addParticipant(Long participantId, Long teamId);

    void deleteParticipant(Long participantId, Long teamId);
}
