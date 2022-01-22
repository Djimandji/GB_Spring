package ru.geekbrains;

import org.hibernate.cfg.Configuration;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();
        em.createQuery(
                "select p " +

                     "from Customer c " +
                     "join c.lineItems li " +
                     "join li.product p " +
                     "where c.id = :customerId ")
                .setParameter("customerId",1L)
                .getResultList();
        em.close();

    }
}
