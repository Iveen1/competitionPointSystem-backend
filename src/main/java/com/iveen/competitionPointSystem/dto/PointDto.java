package com.iveen.competitionPointSystem.dto;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:19
 * @project competitionPointSystem
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PointDto {
    private Long id;

    @NotNull
    @DecimalMax(value = "1", message = "Max value is 1")
    @DecimalMin(value = "0", message = "Min value is 0")
    private Double coefficient;

    @NotNull
    private TaskDto task;

    @NotNull
    private ParticipantDto participant;

    @Override
    public String toString() {
        return "PointDto{" +
                "id=" + id +
                ", coefficient=" + coefficient +
                ", task=" + task +
                ", participant=" + participant +
                '}';
    }
}
