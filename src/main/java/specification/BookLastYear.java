package specification;

import entity.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BookLastYear extends AbstractSpecification<Book> {
    private Calendar date = Calendar.getInstance();

    public Predicate toPredicate(Root root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(root.<Calendar>get("addDate"), date);
    }
}
