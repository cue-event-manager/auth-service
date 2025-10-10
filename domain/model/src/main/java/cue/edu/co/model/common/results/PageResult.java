package cue.edu.co.model.common.results;

import lombok.Builder;

import java.util.List;

@Builder
public record PageResult<T>(
        List<T> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious
) {
}