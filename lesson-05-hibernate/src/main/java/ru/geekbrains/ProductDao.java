package ru.geekbrains;


import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.parser.Entity;
import java.util.List;



public class ProductDao {

    EntityManagerFactory emFactory;

    public ProductDao (EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    Product findById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;

    }

    List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> products;
        em.getTransaction().begin();
        products = em.createQuery("SELECT products FROM Product products", Product.class).getResultList();
        em.getTransaction().commit();
        em.close();
        for (Product pr : products) {
            System.out.println(pr.getTitle());
        }
        return products;
    }
    void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    Product saveOrUpdate(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
        return product;
    }
}
