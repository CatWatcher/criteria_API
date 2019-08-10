import entity.Article;
import entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ArticleMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("OrmExample");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Author author = new Author();
        author.setName("Jhon Snow");

//        for (int i = 1; i < 10; i++) {
//            Article article = new Article();
//            article.setTitle("Article: " + i);
//            article.setAuthor(author);
//            article.setId(i);
//
//            entityManager.persist(article);
//            entityManager.persist(author);
//        }


        Author author1 = entityManager.find(Author.class, 51);
        List<Article> articles = author1.getArticleList();

        for (Article article : articles) {
            System.out.println(article.getTitle());
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }
}
