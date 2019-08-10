package entity;

import javax.persistence.*;
import java.util.Calendar;


@Entity // обязательно, говорит что класс будет связан с базой данных
@Table(name = "Books")  // говорит что этот класс будет табличкой
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),  // каждый запрос начинается с неймквери чпрез запятую
        // обязательно ставим псевдоним.поле (b.title)
        @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title")
}) // для именнованных запросов
public class Book {
    @Id // говорим что это первичный ключ
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @Column(name = "Title", nullable = false,
//            insertable = false, updatable = true, length = 255,
//            columnDefinition = "VARCHAR(255) NOT NULL") // правила для поля
//                            // нуллбл - можно ли писать нулевое значение
//                            // инсертбл - можно ли делать вставку
//                            // колюмнДефинишн - правила на СКЛ
    private String title;
    private int pageCount;
    @Temporal(TemporalType.TIMESTAMP) // как будет отображаться дата
    private Calendar addDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Calendar getAddDate() {
        return addDate;
    }

    public void setAddDate(Calendar addDate) {
        this.addDate = addDate;
    }

}
