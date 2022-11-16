package com.iveen.competitionPointSystem.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ParticipantDto> participants;
}
