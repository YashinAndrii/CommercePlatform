package com.example.CommercePlatform.Controllers;

import com.example.CommercePlatform.Entities.Basket;
import com.example.CommercePlatform.Repositories.BasketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class BasketController {
    BasketRepository basketRepository;
    private static final long USER_ID = 1;
    public BasketController(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @GetMapping("/basket")
    @ResponseBody
    public String showAllBasket() {
        basketRepository.findById(USER_ID);

        return basketRepository.findById(USER_ID).toString();
    }

    @PostMapping("/basket/delete/{id}")
    @ResponseBody
    public String deleteFromBasket(@PathVariable long id) {
        Optional<Basket> basketOptional = basketRepository.findById(USER_ID);

        if(basketOptional.isEmpty()) {
            return "No basket is ready yet";
        }

        Basket basket = basketOptional.get();
        basket.removeProductById(id);

        basketRepository.save(basket);

        return "Deleted successfully from basket" + id;
    }
}
