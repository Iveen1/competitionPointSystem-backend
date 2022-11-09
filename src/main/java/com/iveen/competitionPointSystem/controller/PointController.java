package com.iveen.competitionPointSystem.controller;
import com.iveen.competitionPointSystem.dto.PointDto;
import com.iveen.competitionPointSystem.service.ParticipantService;
import com.iveen.competitionPointSystem.service.PointService;
import com.iveen.competitionPointSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Validator;

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
    private final TaskService taskService;
    private final ParticipantService participantService;
    private final Validator validator;

    @PostMapping("/create")
    public ResponseEntity<?> createPoint(@RequestParam Long taskId, @RequestParam Long participantId, @RequestParam Double coefficient) {

        PointDto pointDto = PointDto.builder()
                .task(taskService.findById(taskId))
                .participant(participantService.findById(participantId))
                .coefficient(coefficient)
                .build();

        if (validator.validate(pointDto).isEmpty()) {
            PointDto point = pointService.create(pointDto);
            return ResponseEntity.ok("success created point with ID " + point.getId() + " for " + pointDto.getParticipant().getLastName() + " " + pointDto.getParticipant().getFirstName());
        } else {
            return ResponseEntity.badRequest().body("Cant create point because of incorrect params");
        }
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
    public ResponseEntity<?> updatePoint(@PathVariable Long id, @RequestParam Long taskId, @RequestParam Long participantId, @RequestParam Double coefficient) {
        PointDto pointDto = pointService.findById(id);
        pointDto.setTask(taskService.findById(taskId));
        pointDto.setParticipant(participantService.findById(participantId));
        pointDto.setCoefficient(coefficient);

        if (validator.validate(pointDto).isEmpty()) {
            pointService.update(id, pointDto);
            return ResponseEntity.ok("success modified point with ID " + id + " for " + pointDto.getParticipant().getLastName() + " " + pointDto.getParticipant().getFirstName());
        } else {
            return ResponseEntity.badRequest().body("Cant modify point because of incorrect params");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePoint(@PathVariable Long id) {
        pointService.delete(id);
        return ResponseEntity.ok("success remove point with id " + id);
    }
}