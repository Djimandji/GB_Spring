package ru.geekbrains.services;


import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class ProductDao {

    EntityManagerFactory emFactory;

    public ProductDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Product findById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;

    }

    public List<Product> findAll() {
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
    public void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    public Product saveOrUpdate(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
        return product;
    }
}
