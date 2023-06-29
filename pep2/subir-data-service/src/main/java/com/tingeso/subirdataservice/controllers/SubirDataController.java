package com.tingeso.subirdataservice.controllers;

import com.tingeso.subirdataservice.entities.SubirDataEntity;
import com.tingeso.subirdataservice.repositories.SubirDataRepository;
import com.tingeso.subirdataservice.services.SubirDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.ArrayList;

@RestController
@RequestMapping("/subir-data")
public class SubirDataController {

    @Autowired
    private SubirDataService subirData;

    @Autowired
    private SubirDataRepository dataRepository;

    @GetMapping("/fileUpload")
    public String main() {
        return "fileUpload";
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ArrayList<SubirDataEntity>> obtenerPorCodigo(@PathVariable("codigo") String codigo){
        ArrayList<SubirDataEntity> datas = dataRepository.findAllByCodigoOrderByDateAsc(codigo);
        return ResponseEntity.ok(datas);
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        subirData.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        String uploadedFileName = file.getOriginalFilename();
        subirData.leerCsv(uploadedFileName);
        return "redirect:/fileUpload";
    }


    @GetMapping("/fileInformation")
    public String listar(Model model) {
        ArrayList<SubirDataEntity> datas = subirData.obtenerData();
        model.addAttribute("datas", datas);
        return "fileInformation";
    }
}
