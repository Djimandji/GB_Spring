package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        ProductDao productDao = new ProductDao(emFactory);
//        productDao.saveOrUpdate(new Product("Apple", 111));
//        productDao.saveOrUpdate(new Product("Orange", 222));
//        productDao.saveOrUpdate(new Product("Banana", 333));
        productDao.findAll();


    }

}
