package com.example.CommercePlatform.Repositories;

import com.example.CommercePlatform.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
