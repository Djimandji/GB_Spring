package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductSpecification;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    public static final Logger logger = LoggerFactory.getLogger(Product.class);

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                            @RequestParam("priceFilter") Optional<String> priceFilter){
        logger.info("Product filter with name pattern {}", nameFilter.orElse(null));
        logger.info("Product filter with price pattern {}", priceFilter.orElse(null));

        //TODO
        Specification<Product> spec = Specification.where(null);
        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            spec.and(ProductSpecification.nameLike(nameFilter.get()));
        }


        model.addAttribute("products", productRepository.findAll(spec));
        return "product";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/new")
    public String create(Model model) {
    model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product_form";
        }
            productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/delete{id}")
    public String delete (@PathVariable("id") Long id) {
        productRepository.delete(productRepository.findById(id).get());
        return "redirect:/product";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
