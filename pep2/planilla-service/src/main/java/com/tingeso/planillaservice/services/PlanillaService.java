package com.tingeso.planillaservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tingeso.planillaservice.entities.PlanillaEntity;

import com.tingeso.planillaservice.models.ProveedorModel;
import com.tingeso.planillaservice.models.SubirDataModel;
import com.tingeso.planillaservice.repositories.PlanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;


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

    public ProveedorModel obtenerProveedorPorCodigo(String codigo){
        ProveedorModel proveedor = restTemplate.getForObject("http://proveedor-service/proveedor/" + codigo, ProveedorModel.class);
        System.out.println(proveedor);
        return proveedor;
    }
    public  List<String> obtenerCodigosData(){
        List<String> codigosData = restTemplate.getForObject("http://proveedor-service/proveedor/codigo", List.class);
        System.out.println(codigosData);
        return codigosData;
    }

    public void reportePlanilla() throws ParseException {
        planillaRepository.deleteAll();
        List<String> listaCodigos = obtenerCodigosData();
        for (String listaCodigo : listaCodigos) {
            calculoPlanilla(listaCodigo);
        }

    }

    public int klsLeche(String codigo){

        Integer totalKlsLecheResultado = 0;
        List<String> proveedorDatas = restTemplate.getForObject("http://subir-data-service/subir-data/" + codigo, List.class);
        for (String proovedorData : proveedorDatas) {
            totalKlsLecheResultado = totalKlsLecheResultado + Integer.parseInt(proovedorData);
        }

        return totalKlsLecheResultado;
    }

    public void calculoPlanilla(String codigo) throws ParseException {

        ProveedorModel proveedorActual = obtenerProveedorPorCodigo(codigo);
        PlanillaEntity planilla = new PlanillaEntity();
        planilla.setQuincena(proveedorActual.getCategoria());
        planilla.setCodigo_proveedor(proveedorActual.getCodigo());
        planilla.setNombre_proveedor(proveedorActual.getNombre());
        planilla.setTotal_kls_leche(klsLeche(codigo));
        planilla.setPago_por_leche(0);
        planilla.setPct_grasa(0);
        planilla.setPago_por_grasa(0);
        planilla.setPct_solidos_totales(0);
        planilla.setPago_por_solidos_totales(0);
        planilla.setNro_dias_envio_leche(0);
        planilla.setPromedio_diario_kls_leche(0);
        planilla.setPct_variacion_leche(0);
        planilla.setPct_variacion_grasa(0);
        planilla.setPct_variacion_st(0);
        planilla.setBonificacion_frecuencia(0);
        planilla.setDcto_variacion_leche(0);
        planilla.setDcto_variacion_grasa(0);
        planilla.setDcto_variacion_st(0);
        planilla.setPago_total(0);
        planilla.setMonto_retencion(0);
        planilla.setMonto_final(0);

        planillaRepository.save(planilla);



    }



}

