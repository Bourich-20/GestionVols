package com.example.BDprojet.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class SimulationController {
    @GetMapping("/simulation")
    public String getSimulationPage() {
        return "simulation";
    }
}
