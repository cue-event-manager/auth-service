package cue.edu.co.jpa.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public abstract class AbstractSpecificationBuilder<E, Q> {

    protected final Q query;

    protected AbstractSpecificationBuilder(Q query) {
        this.query = query;
    }

    protected abstract void buildPredicates(List<Predicate> predicates,
                                            Root<E> root,
                                            CriteriaBuilder cb);

    public Specification<E> build() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            buildPredicates(predicates, root, cb);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }


    protected <T> void addIfPresent(Optional<T> optionalValue,
                                    List<Predicate> predicates,
                                    Function<T, Predicate> predicateFunction) {
        optionalValue.ifPresent(value -> predicates.add(predicateFunction.apply(value)));
    }


    protected Predicate likeIgnoreCase(CriteriaBuilder cb, Path<String> path, String value) {
        return cb.like(cb.lower(path), likePattern(value));
    }

    protected String likePattern(String value) {
        return "%" + value.trim().toLowerCase() + "%";
    }


    protected <T> Predicate equalIfNotNull(CriteriaBuilder cb, Path<T> path, T value) {
        return value != null ? cb.equal(path, value) : cb.conjunction();
    }
}
