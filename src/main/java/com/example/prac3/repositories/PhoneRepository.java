package com.example.prac3.repositories;

import com.example.prac3.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
