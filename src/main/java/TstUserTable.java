import entity.UserTable;


import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TstUserTable {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("OrmExample");

        // с помощью этого менеджера мы сможем взаимоджействовать с базой данных
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // запишем пользователя
        // создаем транзакцию (открываем) - говорим что начинаем что-то делать в бд
        entityManager.getTransaction().begin();

        // создаем объект
        UserTable userTable = new UserTable();
        userTable.setLogin("qwerty");

        // чтобы добавить объект в базу данных
        entityManager.persist(userTable);

        // закрываем транзакцию и подтверждаем действие
        entityManager.getTransaction().commit();

        // .rollback - откатывает все действия к бегин

        // закрываем менеджер и фабрику
       // entityManager.close();
        //entityManagerFactory.close();

        // получение данных пользователя
        entityManager.getTransaction().begin();

        UserTable userFromDb = entityManager.find(UserTable.class, 1); // указываем класс и айди
        System.out.println(userFromDb.getLogin());
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();


    }
}
