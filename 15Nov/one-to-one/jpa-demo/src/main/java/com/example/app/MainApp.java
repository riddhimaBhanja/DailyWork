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

            // --- OneToOne example ---
            Address addr = new Address("123 Main St", "Bhubaneswar", "751001");
            User user = new User("Riddhima", "riddhima@example.com");
            user.setAddress(addr);
            em.persist(user); // address persisted because of CascadeType.ALL

           

            em.getTransaction().commit();

    
            TypedQuery<User> q1 = em.createQuery("select u from User u", User.class);
            List<User> users = q1.getResultList();
            System.out.println("Users count: " + users.size());
            users.forEach(u -> {
                System.out.println("User: " + u.getName() + " addr: " +
                                   (u.getAddress() != null ? u.getAddress().getStreet() : "none"));
            });

          

        } finally {
            em.close();
            JPAUtil.shutdown();
        }
    }
}
