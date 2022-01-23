package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.geekbrains.services.CustomerDao;
import ru.geekbrains.services.DetailService;
import ru.geekbrains.services.EMService;
import ru.geekbrains.services.ProductDao;

@ComponentScan("ru.geekbrains.services")
@Configuration
public class Config {

    @Bean
    @Scope("singleton")
    public EMService emService() {
        return new EMService();
    }

    @Bean
    @Scope("singleton")
    public DetailService detailService() {
        return new DetailService(emService().getEmFactory());
    }

    @Bean
    @Scope("prototype")
    public CustomerDao customerDao() {
        return new CustomerDao(emService().getEmFactory());
    }

    @Bean
    @Scope("prototype")
    public ProductDao productDao() {
        return new ProductDao(emService().getEmFactory());
    }


}
