package com.example.app;

import com.example.model.*;
import com.example.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // --- ManyToMany example ---
            Student s1 = new Student("Alice");
            Student s2 = new Student("Bob");
            Course c1 = new Course("Maths");
            Course c2 = new Course("Physics");

            s1.addCourse(c1);
            s1.addCourse(c2);

            s2.addCourse(c2);

          
            em.persist(s1);
            em.persist(s2);

            em.getTransaction().commit();

            TypedQuery<Student> q2 = em.createQuery("select s from Student s", Student.class);
            List<Student> students = q2.getResultList();
            System.out.println("Students and their courses:");
            for (Student s : students) {
                System.out.println("- " + s.getName());
                s.getCourses().forEach(c -> System.out.println("   * " + c.getTitle()));
            }

        } finally {
            em.close();
            JPAUtil.shutdown();
        }
    }
}
