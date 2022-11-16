package com.iveen.competitionPointSystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Polyakov Anton
 * @created 09.11.2022 13:12
 * @project competitionPointSystem
 */

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ParticipantUpdateRequest {
    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;
}
