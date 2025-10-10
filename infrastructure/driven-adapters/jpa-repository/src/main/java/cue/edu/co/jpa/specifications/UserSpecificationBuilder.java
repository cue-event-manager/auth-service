package cue.edu.co.jpa.specifications;

import cue.edu.co.jpa.constants.UserColumn;
import cue.edu.co.jpa.entities.UserEntity;
import cue.edu.co.model.user.queries.UserPaginationQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class UserSpecificationBuilder extends AbstractSpecificationBuilder<UserEntity, UserPaginationQuery> {

    private UserSpecificationBuilder(UserPaginationQuery query) {
        super(query);
    }

    public static Specification<UserEntity> build(UserPaginationQuery query) {
        return new UserSpecificationBuilder(query).build();
    }

    @Override
    protected void buildPredicates(List<Predicate> predicates,
                                   Root<UserEntity> root,
                                   CriteriaBuilder cb) {

        addIfPresent(query.name(), predicates, name ->
                cb.or(
                        likeIgnoreCase(cb, root.get(UserColumn.FIRST_NAME), name),
                        likeIgnoreCase(cb, root.get(UserColumn.LAST_NAME), name)
                )
        );

        addIfPresent(query.identification(), predicates,
                id -> likeIgnoreCase(cb, root.get(UserColumn.IDENTIFICATION), id)
        );

        addIfPresent(query.roleId(), predicates,
                roleId -> cb.equal(root.get(UserColumn.ROLE).get(UserColumn.ROLE_ID), roleId)
        );
    }
}