package entity;

import javax.persistence.*;

// сообщаем орму что класс будет табюлицей
// заменяет запрос Create table
@Entity
@Table
public class UserTable {
//    1 вариант
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // как будет создаваться айди

    // 2 вариант
//    @Id
//    @TableGenerator(
//            name = "tgen",
//            allocationSize = 1, // промежуток ай ди
//            initialValue = 1    // с какого начнем
                            // тт.е фйди будут 1, 2, 3, и тд
//    )
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tgen")


    // 3 вариант
    @Id
    @SequenceGenerator(name = "sgenerator", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sgenerator")
    private int id; // столбец id
                    // поля становятся столбцами
    private String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}


// META-INF - в персистенс описываем взаимодействие с базой данных