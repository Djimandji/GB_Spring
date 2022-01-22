package ru.geekbrains.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "customers")
public class Customer {

        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column (nullable = false)
        private String name;

//        @ManyToMany
//        private List<Product> products;

        @OneToMany (mappedBy = "customer",
                    orphanRemoval = true
        )
        private List<LineItem> lineItems;

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Customer() {
        }

        public Customer(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

