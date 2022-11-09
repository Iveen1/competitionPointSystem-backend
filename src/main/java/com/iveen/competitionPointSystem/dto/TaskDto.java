package com.iveen.competitionPointSystem.dto;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:17
 * @project competitionPointSystem
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskDto {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Long maxPoints;
}
