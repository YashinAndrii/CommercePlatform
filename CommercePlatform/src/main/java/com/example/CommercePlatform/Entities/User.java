package com.example.CommercePlatform.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    long id;
    String name;

    public long getId() {
        return id;
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public User(){}

    public String getName() {
        return name;
    }
}
