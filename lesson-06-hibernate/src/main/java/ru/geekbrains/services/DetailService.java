package ru.geekbrains.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Scope ("singleton")
public class DetailService {

    EntityManagerFactory emFactory;

    public DetailService(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }


    public void customerProducts(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT c " +
                "FROM Customer c " +
                "join c.lineItems li " +
                "join li.product p " +
                "WHERE c.id = :customerId")
                .setParameter("customerId", id)
                .getResultList();
        em.getTransaction().commit();
        em.close();
    }

    public void productsCustomers (Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT p " +
                "FROM Product p " +
                "join p.lineItems li " +
                "join li.customer c " +
                "WHERE p.id = :productId")
                .setParameter("productId", id)
                .getResultList();
        em.getTransaction().commit();
        em.close();
    }

    public void detailBuy (Long customerId, Long productId) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT li " +
                "FROM LineItem li " +
                "join li.customer c " +
                "join li.product p " +
                "WHERE p.id = :productId AND c.id = :customerId " )
                .setParameter("productId", productId)
                .setParameter("customerId", customerId)
                .getResultList();
        em.getTransaction().commit();
        em.close();
    }
}

