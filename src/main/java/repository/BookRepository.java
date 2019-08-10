package repository;

import entity.Book;
import specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookRepository implements Repository<Book, Integer> {

    EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(Book book) {
        entityManager.getTransaction().begin(); // начинаем транзакцию

        entityManager.persist(book); // добавление в базу (формирует запрос)

        entityManager.getTransaction().commit();  // подтверждаем транзакцию
    }

    public void delete(Integer integer) {
        entityManager.getTransaction().begin();
        Book book = getByPk(integer); // получаем книгу по айди
        entityManager.remove(book); // удаляем
        entityManager.getTransaction().commit();
    }

    public void update(Book book) {
        entityManager.getTransaction().begin();
        entityManager.merge(book); // обновление записи, поиск будет по айди
        entityManager.getTransaction().commit();
    }

    public Book getByPk(Integer integer) {
        // передаем ссылку на клас и идентификатор
        return entityManager.find(Book.class, integer);
    }

    public List<Book> getAll() {
        // 1 вариант - именованные запросы
        // описываются в аннотациях книг
        // формируем объект из запроса
//        TypedQuery<Book> query = entityManager.createNamedQuery("Book.findAll", Book.class);
        // получаем результат
//        List<Book> books = query.getResultList();

        // 2 вариант - JPQL - запрос прописываем прямо тут
        Query queryJPQL = entityManager.createQuery("SELECT b FROM Book b");
        List<Book> books = (List<Book>) queryJPQL.getResultList();

        // 3 вариант - criteria API
        // формируем запрос
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
//        Root<Book> root = criteriaQuery.from(Book.class);
//        criteriaQuery.select(root);
//        // теперь формируем объект на основе запроса
//        TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
//        List<Book> books = typedQuery.getResultList();

        return books;
    }

    public Book getBookByTitle (String title) {
//        // named query
//        TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByTitle", Book.class);
//        query.setParameter("title", title);  // передаем имя параметра и значение для запроса
//                                                // тут title - это title после двоеточия в аннотации к книге
//        Book book = query.getSingleResult();
//        // JPQL
//        TypedQuery<Book> typedQuery =
//                entityManager.createQuery("SELECT b FROM Book b WHERE title = :title", Book.class);
//        typedQuery.setParameter("title", title);
//        Book book1 = typedQuery.getSingleResult();

        // Criteria API
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        // селект на всю таблицу
        criteriaQuery.select(root);
        // берем столбец тайтл и сравниваем с тайтлом из метода
        Predicate condition = criteriaBuilder.equal(root.<String>get("title"), title);
        // селект на запись с условием
        criteriaQuery.select(root).where(condition); // тут передали условия и проверяем выполняется ли
        // создаем объект запроса
        TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
        Book book = query.getSingleResult();
        return book;
    }

    public List<Book> getBySpecification(Specification spec) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(spec.getType());
        Root<Book> root = criteriaQuery.from(spec.getType());

        Predicate condition = spec.toPredicate(root, criteriaBuilder);
        criteriaQuery.where(condition);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
