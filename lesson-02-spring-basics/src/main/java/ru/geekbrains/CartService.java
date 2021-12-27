package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CartService {

    @Autowired
    private ProductRepository productRepository;
    private final long id = (long)(Math.random() * 1000);
    private final Map<Long, Product> productMap = new HashMap<>();


    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id) {
        return productMap.get(id);
    }


    public void addProduct (Product product) {
        productMap.put(product.getId(), product);
        System.out.println("Product was added");
        showProduct();
    }

    public void deleteProduct(long id) {
        if (findById(id) != null) {
            productMap.remove(id);
            System.out.println("Product was deleted");
            showProduct();
        }
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void showProduct() {
        System.out.println("Products in cart " + id);
        for (Product product : findAll()) {
            System.out.println("Product id = " + product.getId() + "||Product name: " + product.getName());
        }
    }

    public void showRepository() {
        System.out.println("Products in repository ");
        for (Product product : productRepository.findAll()) {
            System.out.println("Product id = " + product.getId() + "||Product name: " + product.getName());
        }
    }


}
