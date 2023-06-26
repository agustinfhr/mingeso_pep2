package com.tingeso.empleadoservice.controllers;

import com.tingeso.empleadoservice.entities.EmpleadoEntity;
import com.tingeso.empleadoservice.entities.ProveedorEntity;
import com.tingeso.empleadoservice.reporitories.ProveedorRepository;
import com.tingeso.empleadoservice.services.EmpleadoService;
import com.tingeso.empleadoservice.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;
    @GetMapping
    public ResponseEntity<List<ProveedorEntity>> obtenerProveedores(){
        List<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        if(proveedores.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping
    public ResponseEntity<ProveedorEntity> guardarProveedor(@RequestBody ProveedorEntity proveedor) {
        String codigo = proveedor.getCodigo();
        String nombre = proveedor.getNombre();
        String categoria = proveedor.getCategoria();
        String retencion = proveedor.getRetencion();
        proveedorService.guardarProveedor(codigo, nombre, categoria, retencion);
        return ResponseEntity.ok(proveedor);
    }


}