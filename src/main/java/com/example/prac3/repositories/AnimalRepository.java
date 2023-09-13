package com.example.prac3.repositories;

import com.example.prac3.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByNameContaining(String name);
}
