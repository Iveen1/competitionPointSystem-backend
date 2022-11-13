package com.iveen.competitionPointSystem.dto;
import com.iveen.competitionPointSystem.domain.entity.Team;
import lombok.*;

import javax.validation.constraints.NotNull;
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

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private TeamDto team;

    private List<PointDto> points;
}
