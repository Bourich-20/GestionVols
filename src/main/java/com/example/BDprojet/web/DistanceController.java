package com.example.BDprojet.web;

import com.example.BDprojet.entities.Aeroport;
import com.example.BDprojet.entities.Avion;
import com.example.BDprojet.entities.Distance;
import com.example.BDprojet.repositories.AeroportRepository;
import com.example.BDprojet.repositories.DistanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@AllArgsConstructor
public class DistanceController {

    @Autowired
    private AeroportRepository aeroportRepository;

    @Autowired
    private DistanceRepository distanceRepository;


    @PostMapping(value = "/saveNvAeroport", consumes = {"application/json"})
    public ResponseEntity<String> ajouterAeroportEtDistances(@RequestBody Aeroport nouvelAeroport) {
        // Enregistrez le nouvel aéroport dans la base de données
        Aeroport aeroportEnregistre = aeroportRepository.save(nouvelAeroport);

        // Récupérez tous les aéroports existants à l'intérieur de cette méthode
        List<Aeroport> tousAeroports = aeroportRepository.findAll();

        // Parcourez tous les autres aéroports
        for (Aeroport autreAeroport : tousAeroports) {
            // Calculez la distance entre le nouvel aéroport et chaque autre aéroport
            Double nouvelleDistance = calculerDistance(aeroportEnregistre, autreAeroport);

            // Créez une nouvelle entrée dans la table Distance
            Distance nouvelleEntreeDistance = new Distance();
            nouvelleEntreeDistance.setId_aeroport1(nouvelAeroport.getId());
            nouvelleEntreeDistance.setId_aeroport2(autreAeroport.getId());
            nouvelleEntreeDistance.setDistance(nouvelleDistance);

            // Enregistrez la nouvelle distance dans la base de données
            distanceRepository.save(nouvelleEntreeDistance);
        }

        return ResponseEntity.ok("{\"message\": \"Aeroport enregistré avec succès\"}");
    }

    private double calculerDistance(Aeroport nvAeroport, Aeroport autreAeroport) {
        double xb = autreAeroport.getX();
        double yb = autreAeroport.getY();
        double xa = nvAeroport.getX();
        double ya = nvAeroport.getY();

        return Math.sqrt(Math.pow(xb - xa, 2) + Math.pow(yb - ya, 2));
    }
    @GetMapping("/MatDistances")
    public ResponseEntity<double[][]> genererMatriceDistancesTriangulaire() {
        // Récupérez toutes les distances depuis la base de données
        List<Distance> distances = distanceRepository.findAll();

        // Trouvez la taille maximale pour initialiser la matrice
        int tailleMaximale = 0;
        for (Distance distance : distances) {
            tailleMaximale = Math.max(tailleMaximale, distance.getId_aeroport1().intValue());
            tailleMaximale = Math.max(tailleMaximale, distance.getId_aeroport2().intValue());
        }

        // Initialiser la matrice des distances triangulaire
        double[][] matriceDistances = new double[tailleMaximale][tailleMaximale];

        // Remplir la matrice des distances avec les valeurs de la base de données
        for (int k = 0; k < tailleMaximale; k++) {
            for (Distance distance : distances) {
                int ligne = distance.getId_aeroport1().intValue() ;
                int colonne = distance.getId_aeroport2().intValue() ;
                if(ligne==k+1)
                { matriceDistances[ligne-1][colonne-1] = distance.getDistance();
                matriceDistances[colonne-1][ligne-1] = distance.getDistance();}


            }

        }



        return ResponseEntity.ok(matriceDistances);
    }
//ici le traill sur le plus petit chemeine avec jekstra
@GetMapping("/chemin/{depart}/{arrivee}")
public ResponseEntity<List<Integer>> trouverChemin(@PathVariable Long depart, @PathVariable Long arrivee) {
    System.out.println("depart" + depart);
    System.out.println("arrivee" + arrivee);

    // Récupérez la matrice des distances depuis la méthode existante
    ResponseEntity<double[][]> responseEntity = genererMatriceDistancesTriangulaire();
    double[][] matriceDistances = responseEntity.getBody();
    System.out.println("Matrice de distances :");
    for (int i = 0; i < matriceDistances.length; i++) {
        for (int j = 0; j < matriceDistances[i].length; j++) {
            System.out.print(matriceDistances[i][j] + " ");
        }
        System.out.println(); // Passer à la ligne suivante pour chaque ligne de la matrice
    }
    // Utiliser l'algorithme de Dijkstra pour trouver le chemin le plus court
    List<Integer> cheminPlusCourt = findAllPathsAndChooseShortest(matriceDistances, depart.intValue() - 1, arrivee.intValue() - 1);

    return ResponseEntity.ok(cheminPlusCourt);
}

    public List<Integer> findAllPathsAndChooseShortest(double[][] distancesMatrix, int startPoint, int endPoint) {
        int n = distancesMatrix.length;

        // Initialize distances to infinity except for the start point
        double[] distance = new double[n];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[startPoint] = 0.0;

        // Priority queue to extract the node with the smallest distance
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(v -> distance[v]));
        priorityQueue.add(startPoint);

        // Map to store paths
        Map<Integer, List<List<Integer>>> paths = new HashMap<>();
        paths.put(startPoint, new ArrayList<>(Arrays.asList(Arrays.asList(startPoint))));

        while (!priorityQueue.isEmpty()) {
            int current = priorityQueue.poll();

            // If the destination point is reached, stop the algorithm
            if (current == endPoint) {
                break;
            }

            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (distancesMatrix[current][neighbor] > 0) {
                    double newDistance = distance[current] + distancesMatrix[current][neighbor];

                    if (newDistance < distance[neighbor]) {
                        distance[neighbor] = newDistance;
                        priorityQueue.add(neighbor);

                        // Update paths
                        paths.computeIfAbsent(neighbor, k -> new ArrayList<>());
                        paths.get(neighbor).clear();  // Remove old paths
                        for (List<Integer> path : paths.get(current)) {
                            List<Integer> newPath = new ArrayList<>(path);
                            newPath.add(neighbor);
                            paths.get(neighbor).add(newPath);
                        }
                    }
                }
            }
        }

        // Reconstruct the shortest path
        List<Integer> shortestPath = paths.getOrDefault(endPoint, Collections.emptyList()).get(0);

        // Iterate through all paths and choose the shortest one
        for (List<Integer> path : paths.get(endPoint)) {
            if (calculatePathDistance(path, distancesMatrix) < calculatePathDistance(shortestPath, distancesMatrix)) {
                shortestPath = path;
            }
        }

        return shortestPath;
    }

    private double calculatePathDistance(List<Integer> path, double[][] distancesMatrix) {
        double distance = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += distancesMatrix[path.get(i)][path.get(i + 1)];
        }
        return distance;
    }



}
