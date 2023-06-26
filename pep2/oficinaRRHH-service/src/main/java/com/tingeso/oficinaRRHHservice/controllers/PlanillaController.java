package com.tingeso.oficinaRRHHservice.controllers;

import com.tingeso.oficinaRRHHservice.entities.OficinaRRHHEntity;
import com.tingeso.oficinaRRHHservice.entities.PlanillaEntity;
import com.tingeso.oficinaRRHHservice.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
@RequestMapping("/Planilla")
public class PlanillaController {

    @Autowired
    private PlanillaService planillaService;

    @GetMapping
    public ResponseEntity<ArrayList<PlanillaEntity>> listarPlanillaCalculo() throws ParseException {

        //if (dataRepository.existsAny() && subirValorRepository.existsAny()) {
        //    System.out.println("\nHay valores!!\n");
        //    planillaService.calcularPlanillas();
        //}
        planillaService.calcularPlanillas();
        ArrayList<PlanillaEntity> planillas = planillaService.obtenerPlanillas();
        //model.addAttribute("planillas", planillas);
        if(planillas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(planillas);
    }

    @GetMapping("/lista-planilla")
    public String listarPlanilla(Model model) {

        ArrayList<PlanillaEntity> planillas = planillaService.obtenerPlanillas();
        model.addAttribute("planillas", planillas);
        return "planillaPago";

    }


}