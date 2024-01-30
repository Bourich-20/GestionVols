package com.example.BDprojet.repositories;

import com.example.BDprojet.entities.Avion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvionRepository extends JpaRepository<Avion,Long> {
}
