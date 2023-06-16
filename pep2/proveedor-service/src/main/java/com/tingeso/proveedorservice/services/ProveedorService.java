package com.tingeso.proveedorservice.services;

import com.tingeso.proveedorservice.reporitories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;



}