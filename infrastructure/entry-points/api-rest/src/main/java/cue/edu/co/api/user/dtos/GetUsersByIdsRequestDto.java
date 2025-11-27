package cue.edu.co.api.user.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record GetUsersByIdsRequestDto(
        @NotEmpty
        List<Long> ids
) {}
