import entity.Group;
import entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class StudentGroupMain {
    public static void main(String[] args) {
        Student Jhin = new Student();
        Student Niko = new Student();
        Student Yuumi = new Student();

        Jhin.setName("Jhin");
        Niko.setName("Niko");
        Yuumi.setName("Yuumi");

        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();

        group1.setGroupName("Programming");
        group2.setGroupName("Mathematics");
        group3.setGroupName("Yoga");
//
//        Jhin.getGroups().add(group1);
//        Jhin.getGroups().add(group2);
//        Yuumi.getGroups().add(group3);
//        Niko.getGroups().add(group2);
//        Niko.getGroups().add(group3);
//
//        group1.getStudents().add(Jhin);
//        group2.getStudents().add(Jhin);
//        group2.getStudents().add(Niko);
//        group3.getStudents().add(Yuumi);
//        group3.getStudents().add(Niko);

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("OrmExample");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
//        entityManager.persist(Jhin);
//        entityManager.persist(Niko);
//        entityManager.persist(Yuumi);

        List<Student> students = entityManager.find(Group.class, 302).getStudents();
        System.out.println("302 group students: ");
        for (Student student : students) {
            System.out.println(student.getName());
        }

        List<Group> groups = entityManager.find(Student.class, 304).getGroups();
        System.out.println("304 student groups: ");
        for (Group group : groups) {
            System.out.println(group.getGroupName());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
