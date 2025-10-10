package cue.edu.co.usecase.user;

import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.model.user.queries.UserPaginationQuery;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserUseCase {
    private final UserRepository userRepository;

    public PageResult<User> execute(UserPaginationQuery userPaginationQuery){
        return userRepository.findAllByFilters(userPaginationQuery);
    }
}
