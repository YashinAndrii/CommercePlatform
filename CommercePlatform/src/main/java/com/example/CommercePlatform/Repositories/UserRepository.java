package com.example.CommercePlatform.Repositories;

import com.example.CommercePlatform.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
