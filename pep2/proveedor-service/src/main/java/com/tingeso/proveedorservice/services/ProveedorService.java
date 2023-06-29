package com.tingeso.proveedorservice.services;


import com.tingeso.proveedorservice.entities.ProveedorEntity;
import com.tingeso.proveedorservice.reporitories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    RestTemplate restTemplate;
    public ArrayList<ProveedorEntity> obtenerProveedores(){
        return (ArrayList<ProveedorEntity>) proveedorRepository.findAll();
    }

    public void guardarProveedor(String codigo, String nombre, String categoria, String retencion){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCodigo(codigo);
        proveedor.setNombre(nombre);
        proveedor.setCategoria(categoria);
        proveedor.setRetencion(retencion);
        proveedorRepository.save(proveedor);
    }

    public List<String> obtenerCodigos() {
        return proveedorRepository.findCodigo();
    }

    public ProveedorEntity findByCodigo(String codigo){
        return proveedorRepository.findByCodigo(codigo);
    }

}
