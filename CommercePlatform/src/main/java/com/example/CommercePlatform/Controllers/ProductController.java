package com.example.CommercePlatform.Controllers;

import com.example.CommercePlatform.Entities.Basket;
import com.example.CommercePlatform.Entities.Product;
import com.example.CommercePlatform.Repositories.BasketRepository;
import com.example.CommercePlatform.Repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ProductController {
    ProductRepository productRepository;
    private static final long USER_ID = 1;
    BasketRepository basketRepository;

    public ProductController(ProductRepository productRepository,
                             BasketRepository basketRepository) {
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
    }

    @GetMapping("/products")
    @ResponseBody
    public String getAllProducts(){
        System.out.println(productRepository.findAll());
        return productRepository.findAll().toString();
    }

    @PostMapping("/add")
    @ResponseBody
    public String addProduct(@RequestParam long id,
                             @RequestParam String name,
                             @RequestParam(defaultValue = "") String description,
                             @RequestParam (defaultValue = "1") long amount,
                             @RequestParam double price) {
        productRepository.save(new Product(id, name, description, amount, price));
        System.out.println(productRepository.findById(id));
        return productRepository.findById(id).toString();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable long id) {
        Optional<Basket> basketOptional = basketRepository.findById(USER_ID);
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()) {
            return "No such product exception";
        }

        if(basketOptional.isPresent()) {
            Basket basket = basketOptional.get();
            Product product = productOptional.get();
            basket.removeProduct(product);
        }

        productRepository.deleteById(id);

        return "Deleted successfully " + id;
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    public String getProductById(@PathVariable long id) {
        System.out.println(productRepository.findById(id));
        return productRepository.findById(id).toString();
    }

    @PostMapping("/addToBasket/{id}")
    @ResponseBody
    public String addToBasket(@PathVariable long id) {
        Optional<Basket> basketOptional = basketRepository.findById(USER_ID);
        Basket basket;
        basket = basketOptional.orElseGet(() -> new Basket(USER_ID));

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            basket.addProduct(product);
            System.out.println(basket.getProducts());
        }else System.out.println("No such product");

        basketRepository.save(basket);

        return basketRepository.findById(USER_ID).toString();
    }
}
