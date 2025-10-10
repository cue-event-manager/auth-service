package cue.edu.co.jpa.mappers;

import cue.edu.co.model.common.enums.SortDirection;
import cue.edu.co.model.common.queries.PaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationMapper {

    default <E, D> PageResult<D> toPageResult(Page<E> page, Function<E, D> mapper) {
        List<D> items = page.getContent().stream()
                .map(mapper)
                .toList();

        return PageResult.<D>builder()
                .items(items)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();
    }

    default Pageable toPageable(PaginationQuery paginationQuery) {
        Sort.Direction direction = paginationQuery.sortDirection() == SortDirection.DESC
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Sort sort = Sort.by(direction, paginationQuery.sortBy());

        return PageRequest.of(paginationQuery.page(), paginationQuery.size(), sort);
    }
}