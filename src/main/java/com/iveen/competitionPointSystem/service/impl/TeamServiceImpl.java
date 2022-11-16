package com.iveen.competitionPointSystem.service.impl;
import com.iveen.competitionPointSystem.domain.entity.Participant;
import com.iveen.competitionPointSystem.domain.entity.Team;
import com.iveen.competitionPointSystem.domain.mapper.TeamMapper;
import com.iveen.competitionPointSystem.domain.repository.ParticipantRepository;
import com.iveen.competitionPointSystem.domain.repository.TeamRepository;
import com.iveen.competitionPointSystem.dto.TeamDto;
import com.iveen.competitionPointSystem.payload.request.TeamUpdateRequest;
import com.iveen.competitionPointSystem.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:34
 * @project competitionPointSystem
 */

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ParticipantRepository participantRepository;
    private final TeamMapper teamMapper;

    @Override
    public TeamDto create(TeamDto teamDto) {
        try {
            return teamMapper.toDto(teamRepository.save(teamMapper.toEntity(teamDto)));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are already team with name " + teamDto.getName());
        }
    }
    @Override
    public TeamDto findById(Long id) {
        return teamMapper.toDto(teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no team with id " + id)));
    }

    @Override
    public TeamDto update(Long id, TeamUpdateRequest teamUpdateRequest) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no team with id " + id));
        team.setName(teamUpdateRequest.getName());
        return teamMapper.toDto(teamRepository.save(team));
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Page<TeamDto> findAll(int page, int size) {
        // за это оторвать мне руки, но пока не нашел иного выхода
        List<Team> entityPage = teamRepository.findAll();
        List<Team> modifiableList = new ArrayList<Team>(entityPage);
        PageRequest pageReq = PageRequest.of(page, size);

        Collections.sort(modifiableList, new Comparator<Team>() {
            @Override
            public int compare(Team team1, Team team2) {
                return team2.getTotalPoints().compareTo(team1.getTotalPoints());
            }
        });

        final int start = (int) pageReq.getOffset();
        final int end = Math.min((start + pageReq.getPageSize()), modifiableList.size());

        return new PageImpl<>(
                teamMapper.toDto(modifiableList).subList(start, end),
                pageReq,
                modifiableList.size()
        );
    }

    @Override
    public void addParticipant(Long participantId, Long teamId) {
        Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "participant with id " + participantId + " not found"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "team with id " + teamId + " not found"));

        List<Participant> newParticipants = team.getParticipants();

        if (newParticipants.contains(participant)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "participant with id " + participantId + " already in team with id " + teamId);
        }
        newParticipants.add(participant);
        participantRepository.save(participant);
    }

    @Override
    public void deleteParticipant(Long participantId, Long teamId) {
        Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "participant with id " + participantId + " not found"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "team with id " + teamId + " not found"));

        List<Participant> newParticipants = team.getParticipants();

        if (!newParticipants.contains(participant)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "participant with id " + participantId + " not in team with id " + teamId);
        }
        newParticipants.remove(participant);
        participantRepository.save(participant);
    }
}
