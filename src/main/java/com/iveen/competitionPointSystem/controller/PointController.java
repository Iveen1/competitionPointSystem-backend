package com.iveen.competitionPointSystem.controller;
import com.iveen.competitionPointSystem.dto.PointDto;
import com.iveen.competitionPointSystem.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 21:47
 * @project competitionPointSystem
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointController {
    private final PointService pointService;

    @PostMapping("/create")
    public ResponseEntity<?> createPoint(@RequestBody PointDto pointDto) {
        pointService.create(pointDto);
        return ResponseEntity.ok("success created point for " + pointDto.getParticipant().getLastName() + "" + pointDto.getParticipant().getFirstName());
    }

    @GetMapping("")
    public Page<PointDto> getPoint(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        return pointService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public PointDto getPoint(@PathVariable Long id) {
        return pointService.findById(id);
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> updatePoint(@PathVariable Long id, @RequestBody PointDto pointDto) {
        pointService.update(id, pointDto);
        return ResponseEntity.ok("success update point for " + pointDto.getParticipant().getLastName() + "" + pointDto.getParticipant().getFirstName());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePoint(@PathVariable Long id) {
        pointService.delete(id);
        return ResponseEntity.ok("success remove point with id " + id);
    }
}
