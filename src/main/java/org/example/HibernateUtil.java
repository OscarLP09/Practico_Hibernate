package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .configure()
                .setProperty("hibernate.connection.password", System.getenv("hibernate_password"))
                .setProperty("hibernate.connection.username", System.getenv("hibernate_username"))
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
