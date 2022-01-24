package ru.geekbrains.persist;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


//@Repository
//public class ProductRepositoryImpl  {
//
//    @PersistenceContext
//    private EntityManager em;
//
//
//
//
//    public List<Product> findAll() {
//        return em.createQuery("select p from Product p", Product.class)
//                .getResultList();
//    }
//
//
//    public Optional<Product> findById(long id) {
//        return Optional.ofNullable(em.find(Product.class, id));
//    }
//
//    @Transactional
//
//    public Product save(Product product) {
//        if (product.getId() == null) {
//            em.persist(product);
//            return product;
//        }
//        return em.merge(product);
//    }
//
//    @Transactional
//
//    public void delete(Product product) {
//        em.createQuery("delete from Product p where id = :id")
//                .setParameter("id", product.getId())
//                .executeUpdate();
//    }
//}
