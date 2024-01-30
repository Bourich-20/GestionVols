package com.example.BDprojet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.example.BDprojet.entities.Aeroport;
import com.example.BDprojet.entities.Avion;


import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date dateDeDepart ;

    @ManyToOne
//    @JsonIgnore
    private Aeroport aeroportDeDepart;
    @ManyToOne
//    @JsonIgnore
    private Aeroport aeroportArrives;
    @ManyToOne


    //@JsonIgnore
    private Avion avion;

}
