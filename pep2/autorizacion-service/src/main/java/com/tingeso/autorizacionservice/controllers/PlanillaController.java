package com.tingeso.autorizacionservice.controllers;

import com.tingeso.autorizacionservice.entities.PlanillaEntity;
import com.tingeso.autorizacionservice.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/planilla-pago")
public class PlanillaController {

    @Autowired
    private PlanillaService planillaService;

    @GetMapping
    public ResponseEntity<List<PlanillaEntity>> listarPlanillaCalculo(){

        List<PlanillaEntity> planillas = planillaService.obtenerPlanillas();
        if(planillas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(planillas);

    }
}