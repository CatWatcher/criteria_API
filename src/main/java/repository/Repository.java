package repository;

import org.eclipse.persistence.jpa.jpql.parser.TrimExpression;
import specification.Specification;

import java.util.List;

public interface Repository<T, PK> {
    void add(T t);
    void delete(PK pk);
    void update(T t);
    T getByPk(PK pk);
    List<T> getAll();
    List<T> getBySpecification(Specification spec);
}
