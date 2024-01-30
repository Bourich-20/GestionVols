package com.example.BDprojet.web;

import com.example.BDprojet.entities.Aeroport;
import com.example.BDprojet.entities.Avion;
import com.example.BDprojet.repositories.AeroportRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class AeroportController {
    private final AeroportRepository aeroportRepository;

    @PostMapping(value = "/saveAeroport",consumes = {"application/json"})
    public ResponseEntity<String> saveAeroport(@RequestBody Aeroport aeroportsdata) {
        System.out.println(aeroportsdata);
        aeroportRepository.save(aeroportsdata);
        return ResponseEntity.ok("{\"message\": \"Aeroport enregistré avec succès\"}");
    }
    @GetMapping("/aeroports")
    public ResponseEntity<List<Aeroport>> getAllAeroports() {
        List<Aeroport> aeroports = aeroportRepository.findAll();
        System.out.println("++++++++++++++++++++" + aeroports);
        return new ResponseEntity<>(aeroports, HttpStatus.OK);
    }
   /* @GetMapping("/aeroports")
    public String aeroport(Model model) {
        List<Aeroport> listAeroports = aeroportRepository.findAll();
        model.addAttribute("listAeroports", listAeroports);
        return "aeroport";
    }*/
    @GetMapping("/deleteAeroport")
    public String delete(@RequestParam Long id) {
        aeroportRepository.deleteById(id);
        return "redirect:/aeroports";
    }

    @GetMapping("/formAeroport")
    public String formAeroport(Model model) {
        model.addAttribute("aeroport", new Aeroport());
        return "formAeroport";
    }

    @GetMapping("/editAeroport")
    public String editAeroport(Model model, @RequestParam(name = "id") Long id) {
        Aeroport aeroport = aeroportRepository.findById(id).orElse(null);
        model.addAttribute("aeroport", aeroport);
        return "editAeroport";
    }
}
