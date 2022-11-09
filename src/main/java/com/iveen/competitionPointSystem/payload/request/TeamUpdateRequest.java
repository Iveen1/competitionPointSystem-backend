package com.iveen.competitionPointSystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Polyakov Anton
 * @created 09.11.2022 13:26
 * @project competitionPointSystem
 */

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class TeamUpdateRequest {
    @NotNull
    private String name;
}
