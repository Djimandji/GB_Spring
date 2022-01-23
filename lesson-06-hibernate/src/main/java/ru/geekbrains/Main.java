package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.entity.Product;
import ru.geekbrains.services.CustomerDao;
import ru.geekbrains.services.DetailService;
import ru.geekbrains.services.ProductDao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ProductDao productDao = context.getBean("productDao", ProductDao.class);
        CustomerDao customerDao = context.getBean("customerDao", CustomerDao.class);
        DetailService detailService = context.getBean("detailService", DetailService.class);
        detailService.detailBuy(1L, 1L);
//        detailService.customerProducts(1L);
//        detailService.productsCustomers(1L);
//        customerDao.findAll();
//        productDao.findAll();


    }
}
