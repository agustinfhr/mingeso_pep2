package com.tingeso.proveedorservice.controllers;

import com.tingeso.proveedorservice.entities.ProveedorEntity;
import com.tingeso.proveedorservice.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/codigo")
    public ResponseEntity<List<String>> obtenerCodigosData(){
        List<String> codigos = proveedorService.obtenerCodigos();
        if(codigos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(codigos);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProveedorEntity> obtenerPorCodigo(@PathVariable("codigo") String codigo){
        ProveedorEntity proveedor = proveedorService.findByCodigo(codigo);
        if(proveedor == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(proveedor);

    }


}