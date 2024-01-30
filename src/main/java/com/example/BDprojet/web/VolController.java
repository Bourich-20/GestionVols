package com.example.BDprojet.web;

import com.example.BDprojet.entities.Aeroport;
import com.example.BDprojet.entities.Avion;
import com.example.BDprojet.entities.Vol;
import com.example.BDprojet.repositories.AeroportRepository;
import com.example.BDprojet.repositories.AvionRepository;
import com.example.BDprojet.repositories.VolRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class VolController {
    private final VolRepository volRepository;
    private final AvionRepository avionRepository;
    private final AeroportRepository aeroportRepository;

    @GetMapping("/vols")
    public String vols(Model model) {
        List<Vol> listeVols = volRepository.findAll();
        model.addAttribute("listeVols", listeVols);
        return "vols";
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @PostMapping(value = "/saveVoldata", consumes = "application/json")
    public ResponseEntity<String> saveVoldata(@RequestBody Vol volRequest) {
        // Vous pouvez effectuer des validations ici avant d'enregistrer les données
        // Assurez-vous que les données reçues sont valides avant de les enregistrer

        // Récupérez les objets Avion et Aeroport correspondants à partir de leurs identifiants
        Avion avion = avionRepository.findById(volRequest.getAvion().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Avion not found with id: " + volRequest.getAvion().getId()));

        Aeroport aeroportDepart = aeroportRepository.findById(volRequest.getAeroportDeDepart().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Aeroport not found with id: " + volRequest.getAeroportDeDepart().getId()));
        Aeroport aeroportArrivees = aeroportRepository.findById(volRequest.getAeroportArrives().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Aeroport not found with id: " + volRequest.getAeroportArrives().getId()));

        // Créez un objet Vol avec les données reçues
        Vol vol = new Vol();
        vol.setAeroportDeDepart(aeroportDepart);
        vol.setAeroportArrives(aeroportArrivees);
        vol.setAvion(avion);
        vol.setDateDeDepart(volRequest.getDateDeDepart());

        // Enregistrez le vol dans la base de données
        volRepository.save(vol);

        // Vous pouvez renvoyer un message de succès si nécessaire
        return ResponseEntity.ok("Vol enregistré avec succès");
    }
    @GetMapping("volsData")
    public ResponseEntity<List<Vol>>getAllVols() {

        List<Vol> vols = volRepository.findAll();
        return new ResponseEntity<>(vols, HttpStatus.OK);
    }

    @GetMapping("/deleteVol")
    public String delete(@RequestParam Long id) {
        volRepository.deleteById(id);
        return "redirect:/vols";
    }

    @GetMapping("/formVol")
    public String formVol(Model model) {
        model.addAttribute("vol", new Vol());
        return "formVol";
    }

    @PostMapping("/saveVol")
    public String saveVol(@ModelAttribute Vol vol) {
        volRepository.save(vol);
        return "redirect:/vols";
    }

    @GetMapping("/editVol")
    public String editVol(Model model, @RequestParam(name = "id") Long id) {
        Vol vol = volRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vol not found with id: " + id));
        model.addAttribute("vol", vol);
        return "editVol";
    }
}
