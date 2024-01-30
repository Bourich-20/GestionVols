package com.example.BDprojet.web;

import com.example.BDprojet.entities.Avion;
import com.example.BDprojet.repositories.AvionRepository;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class AvionController {
    private AvionRepository avionRepository ;
    @GetMapping("/avions")
    public String avion(Model model ){
        List<Avion> listAvions=avionRepository.findAll();
        model.addAttribute("listeAvions",listAvions);
        return "avions";
    }
    @PostMapping("/saveAviondata")
    public ResponseEntity<Map<String, String>> saveAvionEtVol(@RequestBody Avion avionInfo) {
        avionRepository.save(avionInfo);
        System.out.println(avionInfo);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Avion et vol enregistrés avec succès");

        return ResponseEntity.ok(response);
    }


    @GetMapping("/delete")
    public String delete(Long id){
        avionRepository.deleteById(id);
        return "redirect:/avions";
    }
    @GetMapping("/avionsAll")
    public ResponseEntity<List<Avion>> getAllAvions() {
        List<Avion> avions =  avionRepository.findAll();
        return new ResponseEntity<>(avions, HttpStatus.OK);
    }
    @GetMapping("/formAvion")
    public String formAvion(Model model){
        model.addAttribute("avion",new Avion());
    return "formAvion";
    }
    @PostMapping("/saveAvion")
    public String saveAvion(Avion avion){
    avionRepository.save(avion);
        return "formAvion";
    }
    @GetMapping("/editAvion")
    public String editAvion(Model model,@RequestParam(name="id") Long id){
        Avion avion = avionRepository.findById(id).get();
        model.addAttribute("avion",avion);
        return "editAvion";
    }

}
