package com.iveen.competitionPointSystem.dto;
import com.iveen.competitionPointSystem.domain.entity.Team;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:14
 * @project competitionPointSystem
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ParticipantDto {
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String firstName;

    @NotBlank
    @Size(min = 3)
    private String lastName;

    private TeamDto team;

    private List<PointDto> points;
}
