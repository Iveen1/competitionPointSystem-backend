package com.iveen.competitionPointSystem.controller;
import com.iveen.competitionPointSystem.dto.ParticipantDto;
import com.iveen.competitionPointSystem.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 21:47
 * @project competitionPointSystem
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    @PostMapping("/create")
    public ResponseEntity<?> createParticipant(@RequestBody ParticipantDto participantDto) {
        participantService.create(participantDto);
        return ResponseEntity.ok("success create participant with name " + participantDto.getFirstName() + " " + participantDto.getLastName());
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
    public ResponseEntity<?> updateParticipant(@PathVariable Long id, @RequestBody ParticipantDto participantDto) {
        participantService.update(id, participantDto);
        return ResponseEntity.ok("success update participant with id " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable Long id) {
        participantService.delete(id);
        return ResponseEntity.ok("success remove participant with id " + id);
    }
}
