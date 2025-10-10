package cue.edu.co.model.common.queries;

import cue.edu.co.model.common.enums.SortDirection;
import lombok.Builder;

@Builder
public record PaginationQuery(
        int page,
        int size,
        String sortBy,
        SortDirection sortDirection
) {
}