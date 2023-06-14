package tingeso.mingeso.pep1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tingeso.mingeso.pep1.repositories.SubirDataRepository;
import tingeso.mingeso.pep1.repositories.SubirValorRepository;
import tingeso.mingeso.pep1.entities.PlanillaEntity;
import tingeso.mingeso.pep1.services.PlanillaService;


import java.util.ArrayList;

@Controller
@RequestMapping
public class PlanillaController {

    @Autowired
    private PlanillaService planillaService;
    @Autowired
    private SubirValorRepository subirValorRepository;
    @Autowired
    private SubirDataRepository dataRepository;

    @GetMapping("/lista-planilla")
    public String listarPlanilla(Model model) {

            ArrayList<PlanillaEntity> planillas = planillaService.obtenerPlanillas();
            model.addAttribute("planillas", planillas);
            return "planillaPago";

    }

    @GetMapping("/lista-planilla-calculo")
    public String listarPlanillaCalculo(Model model) {

            if (dataRepository.existsAny() && subirValorRepository.existsAny()) {
                System.out.println("\nHay valores!!\n");
                planillaService.calcularPlanillas();
            }

            ArrayList<PlanillaEntity> planillas = planillaService.obtenerPlanillas();
            model.addAttribute("planillas", planillas);
            return "planillaPago";
    }

}
