package specification;

import entity.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BookByPages extends AbstractSpecification<Book> {
    int pageCount;

    public BookByPages(int pageCount) {
        this.pageCount = pageCount;
    }

    public Predicate toPredicate(Root root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(root.<Integer>get("pageCount"), pageCount);
    }
}
