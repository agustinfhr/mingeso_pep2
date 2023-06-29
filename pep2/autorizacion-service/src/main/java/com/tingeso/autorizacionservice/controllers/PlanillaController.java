package com.tingeso.autorizacionservice.controllers;

import com.tingeso.autorizacionservice.entities.PlanillaEntity;
import com.tingeso.autorizacionservice.repositories.PlanillaRepository;
import com.tingeso.autorizacionservice.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/planilla-pago")
public class PlanillaController {

    @Autowired
    private PlanillaService planillaService;

    @Autowired
    private PlanillaRepository planillaRepository;

    @GetMapping
    public ResponseEntity<ArrayList<PlanillaEntity>> planillaDePagos() throws ParseException {

        planillaService.calculoPlanilla();
        ArrayList<PlanillaEntity> planillaPagos = planillaService.obtenerPlanillas();
        if(planillaPagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(planillaPagos);

    }
}