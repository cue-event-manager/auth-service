package cue.edu.co.api.user.dtos;


public record UserPaginationRequestDto(
    String name,
    String identification,
    Long roleId
) {
}
