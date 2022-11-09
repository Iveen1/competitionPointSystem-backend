package com.iveen.competitionPointSystem.dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:19
 * @project competitionPointSystem
 */

@Getter
@Setter
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
}
