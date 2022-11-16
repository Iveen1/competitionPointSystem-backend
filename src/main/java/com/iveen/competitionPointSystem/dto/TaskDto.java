package com.iveen.competitionPointSystem.dto;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String title;

    @NotNull
    @Min(1)
    private Long maxPoints;
}
