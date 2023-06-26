package com.tingeso.justificativoservice.controllers;

import com.tingeso.justificativoservice.entities.SubirValorEntity;
import com.tingeso.justificativoservice.services.SubirValorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.ArrayList;

@RestController
@RequestMapping("/subir-valor")
public class SubirValorController {

    @Autowired
    private SubirValorService subirValor;

    @GetMapping("/fileValorUpload")
    public String main() {
        return "fileValorUpload";
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        subirValor.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        String uploadedFileName = file.getOriginalFilename();
        subirValor.leerCsv(uploadedFileName);
        return "redirect:/fileValorUpload";
    }


    @GetMapping("/fileValorInformation")
    public String listar(Model model) {
        ArrayList<SubirValorEntity> valores = subirValor.obtenerValor();
        model.addAttribute("valores", valores);
        return "fileValorInformation";
    }
}
