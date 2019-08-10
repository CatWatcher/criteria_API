package specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

// для двух условий
public class TwoSpecification<T> extends AbstractSpecification {
    private Specification<T> first;
    private Specification<T> second;

    public TwoSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    public Predicate toPredicate(Root root, CriteriaBuilder criteriaBuilder) {

        return criteriaBuilder.and(
                first.toPredicate(root, criteriaBuilder), // метод энд объединяет условия (предикаты)
                second.toPredicate(root, criteriaBuilder)
        );
    }

    @Override
    public Class getType() {
        return first.getType();
    }
}
