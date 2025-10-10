package cue.edu.co.api.common.dtos;

import cue.edu.co.api.common.validations.ValueOfEnum;
import cue.edu.co.model.common.constants.PaginationConstant;
import cue.edu.co.model.common.enums.SortDirection;
import jakarta.validation.constraints.Max;

public record PaginationRequestDto(
        Integer page,
        @Max(PaginationConstant.MAX_SIZE)
        Integer size,
        String sortBy,
        @ValueOfEnum(enumClass = SortDirection.class)
        String sortDirection
) { }