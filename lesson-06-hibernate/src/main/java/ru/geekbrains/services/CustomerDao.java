package ru.geekbrains.services;


import ru.geekbrains.entity.Customer;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class CustomerDao{

    EntityManagerFactory emFactory;

    public CustomerDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    Customer findById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;

    }

    public List<Customer> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Customer> customers;
        em.getTransaction().begin();
        customers = em.createQuery("SELECT customers FROM Customer customers", Customer.class).getResultList();
        em.getTransaction().commit();
        em.close();
        for (Customer cs : customers) {
            System.out.println(cs.getName());
        }
        return customers;
    }
    public void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        em.remove(customer);
        em.getTransaction().commit();
        em.close();
    }

    public Customer saveOrUpdate(Customer customer) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
        return customer;
    }
}
