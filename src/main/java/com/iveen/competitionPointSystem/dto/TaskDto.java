package com.iveen.competitionPointSystem.dto;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:17
 * @project competitionPointSystem
 */

@Getter
@Setter
public class TaskDto {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Long maxPoints;
}
