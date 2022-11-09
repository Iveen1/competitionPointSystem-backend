package com.iveen.competitionPointSystem.controller;
import com.iveen.competitionPointSystem.dto.TeamDto;
import com.iveen.competitionPointSystem.payload.request.TeamUpdateRequest;
import com.iveen.competitionPointSystem.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 21:47
 * @project competitionPointSystem
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/create")
    public ResponseEntity<?> createTeam(@RequestBody TeamDto teamDto) {
        TeamDto team = teamService.create(teamDto);
        return ResponseEntity.ok("success created team with id " + team.getId());
    }

    @GetMapping("")
    public Page<TeamDto> getTeam(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        return teamService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public TeamDto getTeam(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody TeamUpdateRequest teamUpdateRequest) {
        teamService.update(id, teamUpdateRequest);
        return ResponseEntity.ok("success update team with id " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.ok("success remove team with id " + id);
    }

    @PostMapping("/participant/add")
    public ResponseEntity<?> addParticipant(@RequestParam Long participantId, @RequestParam Long teamId) {
        teamService.addParticipant(participantId, teamId);
        return ResponseEntity.ok("success added participant with id " + participantId + " to team with id " + teamId);
    }

    @PostMapping("/participant/delete")
    public ResponseEntity<?> deleteParticipant(@RequestParam Long participantId, @RequestParam Long teamId) {
        teamService.deleteParticipant(participantId, teamId);
        return ResponseEntity.ok("success delete participant with id " + participantId + " to team with id " + teamId);
    }
}
