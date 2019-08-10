package specification;

import entity.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BookByTitle extends AbstractSpecification<Book> {
    private String title;

    public BookByTitle(String title) {
        this.title = title;
    }

    public Predicate toPredicate(Root root, CriteriaBuilder criteriaBuilder) {
        // тут формируем условие
        return criteriaBuilder.equal(root.<String>get("title"), title);
    }
}
