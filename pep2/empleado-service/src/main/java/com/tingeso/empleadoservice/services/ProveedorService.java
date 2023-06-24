package com.tingeso.empleadoservice.services;


import com.tingeso.empleadoservice.entities.EmpleadoEntity;
import com.tingeso.empleadoservice.entities.ProveedorEntity;
import com.tingeso.empleadoservice.reporitories.ProveedorRepository;
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

}
