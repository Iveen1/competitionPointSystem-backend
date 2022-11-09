package com.iveen.competitionPointSystem.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:16
 * @project competitionPointSystem
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TeamDto {
    private Long id;

    @NotNull
    private String name;

    @JsonIgnore
    private List<ParticipantDto> participants;
}
