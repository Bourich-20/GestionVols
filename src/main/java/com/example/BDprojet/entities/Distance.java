package com.example.BDprojet.entities;
import lombok.Data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name="id_aeroport1")
    private Long id_aeroport1;

    @Column(name="id_aeroport2")
    private Long id_aeroport2;

    private Double distance;
}