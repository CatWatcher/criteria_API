import entity.Book;
import repository.BookRepository;
import specification.BookByPages;
import specification.BookByTitle;
import specification.BookLastYear;
import specification.TwoSpecification;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.GregorianCalendar;
import java.util.List;

public class MainBookRepository {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("OrmExample");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BookRepository repository = new BookRepository(entityManager);

        // добавление книг в бд
//        for (int i = 1; i < 6; i++) {
//            Book book = new Book();
//            book.setTitle("Book: " + i);
//            book.setPageCount(i);
//            book.setAddDate(new GregorianCalendar());
//            book.setPageCount(i + 99);
//            repository.add(book); // добавляем в базу
//        }
        // получаем книгу по айди
        Book bookById = repository.getByPk(4);
        System.out.println(bookById.getTitle());

        bookById.setTitle("Game of thrones");
        repository.update(bookById);

        //repository.delete(2);

        List<Book> books = repository.getAll();
        for (Book book : books) {
            System.out.println(book.getTitle());
        }

        List<Book> bookByTitle = repository.getBySpecification(new BookByTitle("Game of thrones"));
        for (Book book : bookByTitle) {
            System.out.println(book.getTitle());
        }

        List<Book> bookList = repository.getBySpecification(new TwoSpecification
                (new BookByTitle("Game of thrones"), new BookByPages(101)));

        List<Book> booksPages = repository.getBySpecification(new BookByPages(101));

        for (Book book : booksPages) {
            System.out.println(book.getId());
        }

        List<Book> bookLastYear = repository.getBySpecification(new BookLastYear());


        entityManager.close();
        entityManagerFactory.close();

    }
}
