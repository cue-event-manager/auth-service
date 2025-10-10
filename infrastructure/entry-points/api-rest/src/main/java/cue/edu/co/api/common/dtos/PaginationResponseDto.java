package cue.edu.co.api.common.dtos;

import java.util.List;

public record PaginationResponseDto<T>(
        List<T> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious
) { }