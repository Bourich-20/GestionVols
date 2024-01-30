package com.example.BDprojet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Avion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name="TypeAvion")
    private String TypeAvion ;
    @Column(name="x")

    private double X ;
    @Column(name="y")

    private double y ;
    @Column(name="NbrDePlaces")

    private int NbrDePlaces ;
    @Column(name="Consomation")

    private Double Consomation ;
    @Column(name="CapaciteCarburan")

    private Double CapaciteCarburan ;
    @Column(name="VitesseNormale")

    private Double VitesseNormale ;
    @Column(name="VitesseAtterrissage")

    private Double VitesseAtterrissage ;
    @Column(name="VitesseCollage")

    private Double VitesseCollage ;
    @Column(name="desination")

    private String desination ;
    @OneToMany(mappedBy = "avion",fetch = FetchType.LAZY )
    @JsonIgnore
    private Collection<Vol> vols ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeAvion() {
        return TypeAvion;
    }

    public void setTypeAvion(String typeAvion) {
        TypeAvion = typeAvion;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getNbrDePlaces() {
        return NbrDePlaces;
    }

    public void setNbrDePlaces(int nbrDePlaces) {
        NbrDePlaces = nbrDePlaces;
    }

    public Double getConsomation() {
        return Consomation;
    }

    public void setConsomation(Double consomation) {
        Consomation = consomation;
    }

    public Double getCapaciteCarburan() {
        return CapaciteCarburan;
    }

    public void setCapaciteCarburan(Double capaciteCarburan) {
        CapaciteCarburan = capaciteCarburan;
    }

    public Double getVitesseNormale() {
        return VitesseNormale;
    }

    public void setVitesseNormale(Double vitesseNormale) {
        VitesseNormale = vitesseNormale;
    }

    public Double getVitesseAtterrissage() {
        return VitesseAtterrissage;
    }

    public void setVitesseAtterrissage(Double vitesseAtterrissage) {
        VitesseAtterrissage = vitesseAtterrissage;
    }

    public Double getVitesseCollage() {
        return VitesseCollage;
    }

    public void setVitesseCollage(Double vitesseCollage) {
        VitesseCollage = vitesseCollage;
    }

    public String getDesination() {
        return desination;
    }

    public void setDesination(String desination) {
        this.desination = desination;
    }

    public Collection<Vol> getVols() {
        return vols;
    }

    public void setVols(Collection<Vol> vols) {
        this.vols = vols;
    }
}
