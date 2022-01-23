package ru.geekbrains.services;

import org.hibernate.cfg.Configuration;
import org.springframework.aop.aspectj.SingletonAspectInstanceFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
@Scope ("singleton")
public class EMService {

    private EntityManagerFactory emFactory;

    @PostConstruct
    public void init() {
        emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public EntityManagerFactory getEmFactory() {
        return emFactory;
    }

    public void setEmFactory(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public EntityManager getEntityManager() {
        EntityManager em = emFactory.createEntityManager();
        return em;
    }
}
