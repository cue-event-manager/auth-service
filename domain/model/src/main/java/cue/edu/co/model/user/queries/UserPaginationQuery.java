package cue.edu.co.model.user.queries;

import cue.edu.co.model.common.queries.PaginationQuery;
import lombok.Builder;

import java.util.Optional;

@Builder
public record UserPaginationQuery(
        Optional<String> name,
        Optional<String> identification,
        Optional<Long> roleId,
        PaginationQuery pagination
) {
}