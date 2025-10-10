package cue.edu.co.api.common.mappers;

import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.model.common.constants.PaginationConstant;
import cue.edu.co.model.common.enums.SortDirection;
import cue.edu.co.model.common.queries.PaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface PaginationDtoMapper {

    default PaginationQuery toPaginationQuery(PaginationRequestDto dto) {
        int page = dto.page() != null && dto.page() >= 0
                ? dto.page()
                : PaginationConstant.DEFAULT_PAGE;

        int size = dto.size() != null && dto.size() > 0
                ? dto.size()
                : PaginationConstant.DEFAULT_SIZE;

        String sortBy = dto.sortBy() != null && !dto.sortBy().isBlank()
                ? dto.sortBy()
                : PaginationConstant.DEFAULT_SORT_BY;

        SortDirection sortDirection = dto.sortDirection() != null
                ? SortDirection.from(dto.sortDirection())
                : PaginationConstant.DEFAULT_SORT_DIRECTION;

        return PaginationQuery.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();
    }
    default <T, D> PaginationResponseDto<D> toDto(PageResult<T> pageResult, List<D> items) {
        return new PaginationResponseDto<>(
                items,
                pageResult.page(),
                pageResult.size(),
                pageResult.totalElements(),
                pageResult.totalPages(),
                pageResult.hasNext(),
                pageResult.hasPrevious()
        );
    }
}
