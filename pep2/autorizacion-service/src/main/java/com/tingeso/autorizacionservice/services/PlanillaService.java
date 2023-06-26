package com.tingeso.autorizacionservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tingeso.autorizacionservice.entities.PlanillaEntity;
import com.tingeso.autorizacionservice.models.ProveedorModel;
import com.tingeso.autorizacionservice.models.SubirDataModel;
import com.tingeso.autorizacionservice.models.SubirValorModel;

import com.tingeso.autorizacionservice.repositories.PlanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PlanillaService {

    @Autowired
    private PlanillaRepository planillaRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;
    public ArrayList<PlanillaEntity> obtenerPlanillas(){
        return (ArrayList<PlanillaEntity>) planillaRepository.findAll();
    }


}


