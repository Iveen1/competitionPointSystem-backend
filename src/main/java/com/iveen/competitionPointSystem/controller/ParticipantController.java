package com.iveen.competitionPointSystem.controller;
import com.iveen.competitionPointSystem.dto.ParticipantDto;
import com.iveen.competitionPointSystem.payload.request.ParticipantUpdateRequest;
import com.iveen.competitionPointSystem.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 21:47
 * @project competitionPointSystem
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    @PostMapping("/create")
    public ResponseEntity<?> createParticipant(@RequestBody @Valid ParticipantDto participantDto) {
        ParticipantDto participant = participantService.create(participantDto);
        return ResponseEntity.ok(participant);
    }

    @GetMapping("")
    public Page<ParticipantDto> getParticipants(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        return participantService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public ParticipantDto getParticipant(@PathVariable Long id) {
        return participantService.findById(id);
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> updateParticipant(@PathVariable Long id, @RequestBody @Valid ParticipantUpdateRequest participantUpdateRequest) {
        participantService.update(id, participantUpdateRequest);
        return ResponseEntity.ok(Collections.singletonMap("response", "success update participant with id " + id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable Long id) {
        participantService.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("response", "success remove participant with id " + id));
    }
}
