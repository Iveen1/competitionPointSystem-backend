package com.iveen.competitionPointSystem.service.impl;
import com.iveen.competitionPointSystem.domain.entity.Participant;
import com.iveen.competitionPointSystem.domain.mapper.ParticipantMapper;
import com.iveen.competitionPointSystem.domain.repository.ParticipantRepository;
import com.iveen.competitionPointSystem.dto.ParticipantDto;
import com.iveen.competitionPointSystem.payload.request.ParticipantUpdateRequest;
import com.iveen.competitionPointSystem.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    @Override
    public ParticipantDto create(ParticipantDto participantDto) {
        return participantMapper.toDto(participantRepository.save(participantMapper.toEntity(participantDto)));
    }

    @Override
    public ParticipantDto findById(Long id) {
        return participantMapper.toDto(participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no user with id " + id)));
    }

    @Override
    public ParticipantDto update(Long id, ParticipantUpdateRequest participantUpdateRequest) {
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no team with id " + id));
        participant.setFirstName(participantUpdateRequest.getFirstName());
        participant.setLastName(participantUpdateRequest.getLastName());
        return participantMapper.toDto(participantRepository.save(participant));
    }

    @Override
    public void delete(Long id) {
        participantRepository.deleteById(id);
    }

    @Override
    public Page<ParticipantDto> findAll(int page, int size) {
        Page<Participant> entityPage = participantRepository.findAll(PageRequest.of(page, size));
        List<Participant> modifiableList = new ArrayList<Participant>(entityPage.toList());

        // Comparator.comparing(Participant::getTotalPoints)
        Collections.sort(modifiableList, new Comparator<Participant>() {
            @Override
            public int compare(Participant participant1, Participant participant2) {
                return participant2.getTotalPoints().compareTo(participant1.getTotalPoints());
            }
        });
        return new PageImpl<>(
                participantMapper.toDto(modifiableList),
                PageRequest.of(page, size),
                entityPage.getTotalElements()
        );
    }
}
