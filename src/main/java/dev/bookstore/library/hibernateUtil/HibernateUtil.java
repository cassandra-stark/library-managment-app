package dev.bookstore.library.hibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    public static SessionFactory getCurrentSessionFromJPA() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("application.properties");
        EntityManager entityManager = emf.createEntityManager();
        // Get the Hibernate Session from the EntityManager in JPA
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        SessionFactory factory = session.getSessionFactory();
        return factory;
    }


}
