package com.iveen.competitionPointSystem.dto;
import com.iveen.competitionPointSystem.domain.entity.Team;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:14
 * @project competitionPointSystem
 */

@Getter
@Setter
public class ParticipantDto {
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private TeamDto team;
}
