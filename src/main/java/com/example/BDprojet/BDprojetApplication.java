package com.example.BDprojet;

import com.example.BDprojet.entities.Aeroport;
import com.example.BDprojet.entities.Avion;
import com.example.BDprojet.entities.Distance;
import com.example.BDprojet.entities.Vol;
import com.example.BDprojet.repositories.AeroportRepository;
import com.example.BDprojet.repositories.AvionRepository;
import com.example.BDprojet.repositories.DistanceRepository;
import com.example.BDprojet.repositories.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BDprojetApplication implements CommandLineRunner {
	@Autowired
private AvionRepository avionRepository ;
	@Autowired
	private AeroportRepository aeroportRepository ;
	@Autowired
	DistanceRepository distanceRepository;

	public Double distance(Aeroport a1, Aeroport a2) {
		return Math.sqrt(Math.pow(a1.getX() - a2.getX(), 2) + Math.pow(a1.getY() - a2.getY(), 2));
	}
	private VolRepository volRepository ;
	public static void main(String[] args) {
		SpringApplication.run(BDprojetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
