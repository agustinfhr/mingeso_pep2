package com.tingeso.subirvalorservice.controllers;

import com.tingeso.subirvalorservice.entities.SubirValorEntity;
import com.tingeso.subirvalorservice.repositories.SubirValorRepository;
import com.tingeso.subirvalorservice.services.SubirValorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subir-valor")
public class SubirValorController {

    @Autowired
    private SubirValorService subirValor;

    @Autowired
    private SubirValorRepository valorRepository;

    @GetMapping
    public ResponseEntity<List<SubirValorEntity>> obtenerValores(){
        List<SubirValorEntity> valores = subirValor.obtenerValor();
        if(valores.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(valores);
    }

    @GetMapping("/fileValorUpload")
    public String main() {
        return "fileValorUpload";
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<SubirValorEntity> obtenerValorPorCodigo(@PathVariable("codigo") String codigo){
        SubirValorEntity valor = valorRepository.findByProveedor(codigo);
        return ResponseEntity.ok(valor);
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
