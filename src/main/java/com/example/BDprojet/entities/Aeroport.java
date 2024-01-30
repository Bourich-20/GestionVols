package com.example.BDprojet.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Aeroport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name="nom")
    private String nom ;
    @Column(name="x")

    private Double x ;
    @Column(name="y")

    private Double y ;
    @Column(name="nbrPistes")

    private int nbrPistes ;
    @Column(name="nbrStations")

    private int nbrStations ;
    @Column(name="tempsAccesAuxPistes")

    private Double tempsAccesAuxPistes ;
    @Column(name="tempsDeDecolageOuAtterrissage")

    private Double tempsDeDecolageOuAtterrissage ;
    @Column(name="delaiAttenteAuSol")

    private Double delaiAttenteAuSol ;
    @Column(name="dureeDeBoucleAttentEnVol")

    private Double dureeDeBoucleAttentEnVol ;
    @Column(name="distance")

    private Double distance ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public int getNbrPistes() {
        return nbrPistes;
    }

    public void setNbrPistes(int nbrPistes) {
        this.nbrPistes = nbrPistes;
    }

    public int getNbrStations() {
        return nbrStations;
    }

    public void setNbrStations(int nbrStations) {
        this.nbrStations = nbrStations;
    }

    public Double getTempsAccesAuxPistes() {
        return tempsAccesAuxPistes;
    }

    public void setTempsAccesAuxPistes(Double tempsAccesAuxPistes) {
        this.tempsAccesAuxPistes = tempsAccesAuxPistes;
    }

    public Double getTempsDeDecolageOuAtterrissage() {
        return tempsDeDecolageOuAtterrissage;
    }

    public void setTempsDeDecolageOuAtterrissage(Double tempsDeDecolageOuAtterrissage) {
        this.tempsDeDecolageOuAtterrissage = tempsDeDecolageOuAtterrissage;
    }

    public Double getDelaiAttenteAuSol() {
        return delaiAttenteAuSol;
    }

    public void setDelaiAttenteAuSol(Double delaiAttenteAuSol) {
        this.delaiAttenteAuSol = delaiAttenteAuSol;
    }

    public Double getDureeDeBoucleAttentEnVol() {
        return dureeDeBoucleAttentEnVol;
    }

    public void setDureeDeBoucleAttentEnVol(Double dureeDeBoucleAttentEnVol) {
        this.dureeDeBoucleAttentEnVol = dureeDeBoucleAttentEnVol;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
