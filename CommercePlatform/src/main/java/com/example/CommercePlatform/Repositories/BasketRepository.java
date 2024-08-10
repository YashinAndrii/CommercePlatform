package com.example.CommercePlatform.Repositories;

import com.example.CommercePlatform.Entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,Long> {
}
