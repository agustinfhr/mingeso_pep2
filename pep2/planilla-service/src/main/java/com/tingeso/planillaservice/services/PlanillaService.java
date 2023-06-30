package com.tingeso.planillaservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tingeso.planillaservice.entities.PlanillaEntity;

import com.tingeso.planillaservice.models.ProveedorModel;
import com.tingeso.planillaservice.models.SubirDataModel;
import com.tingeso.planillaservice.models.SubirValorModel;
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

    public int pagoPorLeche(String categoriaProveedor, Integer klsLecheProveedor){

        switch(categoriaProveedor){
            case "A":
                return (klsLecheProveedor * 700);
            case "B":
                return (klsLecheProveedor * 550);
            case "C":
                return (klsLecheProveedor * 400);
            case "D":
                return (klsLecheProveedor * 250);
            default:
                return 0;
        }
    }

    public int pagoPorGrasa(Integer klsLecheProveedor, String codigo, Integer interruptor){

        int pctGrasa = 0;

        SubirValorModel proveedorValores = restTemplate.getForObject("http://subir-valor-service/subir-valor/" + codigo, SubirValorModel.class);
        pctGrasa = Integer.parseInt(proveedorValores.getPct_grasa());

        if (interruptor == 1) {
            return pctGrasa;
        } else if (pctGrasa >= 0 && pctGrasa <= 20) {
            return (klsLecheProveedor * 30);
        } else if (pctGrasa >= 21 && pctGrasa <= 45) {
            return (klsLecheProveedor * 80);
        } else if (pctGrasa >= 46) {
            return (klsLecheProveedor * 120);
        } else {
            return 0;
        }
    }

    public int pagoPorST(Integer klsLecheProveedor, String codigo, Integer interruptor){

        int pctST = 0;

        SubirValorModel proveedorValores = restTemplate.getForObject("http://subir-valor-service/subir-valor/" + codigo, SubirValorModel.class);
        pctST = Integer.parseInt(proveedorValores.getPct_solido_total());

        if (interruptor == 1) {
            return pctST;
        } else if (pctST >= 0 && pctST <= 7) {
            return (klsLecheProveedor * -130);
        } else if (pctST >= 8 && pctST <= 18) {
            return (klsLecheProveedor * -90);
        } else if (pctST >= 19 && pctST <= 35) {
            return (klsLecheProveedor * 95);
        } else if (pctST >= 36) {
            return (klsLecheProveedor * 150);
        } else {
            return 0;
        }
    }

    public int totalNroDiasLeche(String codigo){

        Integer totalNroDiasResultado = 0;
        List<String> proveedorDatas = restTemplate.getForObject("http://subir-data-service/subir-data/" + codigo, List.class);
        for (String proovedorData : proveedorDatas) {
            totalNroDiasResultado = totalNroDiasResultado + 1;
        }

        return totalNroDiasResultado;
    }

    public int promklsLeche(Integer totalKlsLeche, Integer nroDiasEnvioLeche){

        int promedio = 0;
        promedio = totalKlsLeche / nroDiasEnvioLeche;
        return promedio;
    }


    public void calculoPlanilla(String codigo) throws ParseException {

        ProveedorModel proveedorActual = obtenerProveedorPorCodigo(codigo);

        int totalKlsLeche = klsLeche(codigo);
        int pagoLeche = pagoPorLeche(proveedorActual.getCategoria(), totalKlsLeche);
        int pctGrasa = pagoPorGrasa(totalKlsLeche,codigo,1);
        int pagoGrasa = pagoPorGrasa(totalKlsLeche,codigo,0);
        int pctSolidosTotales = pagoPorST(totalKlsLeche,codigo,1);
        int pagoSolidosTotales = pagoPorST(totalKlsLeche,codigo,0);
        int nroDiasEnvioLeche = totalNroDiasLeche(codigo);
        int promDiarioKlsLeche = promklsLeche(totalKlsLeche,nroDiasEnvioLeche);
        //int pctVariacionLeche = 0;
        //int pctVariacionGrasa = 0;
        //int pctVariacionST = 0;
        //int manana = 0;
        //int tarde = 0;
        //int bonificacionFrecuencia = 0;
        //int pagoAcopioLeche = 0;
        //int dctoVariacionLeche = 0;
        //int dctoVariacionGrasa = 0;
        //int dctoVariacionST = 0;
        //int descuentos = 0;
        //int pagoTotal = 0;
        //int montoRetencion = 0;
        //int montoFinal = 0;

        PlanillaEntity planilla = new PlanillaEntity();
        planilla.setQuincena(proveedorActual.getCategoria());
        planilla.setCodigo_proveedor(proveedorActual.getCodigo());
        planilla.setNombre_proveedor(proveedorActual.getNombre());
        planilla.setTotal_kls_leche(totalKlsLeche);
        planilla.setPago_por_leche(pagoLeche);
        planilla.setPct_grasa(pctGrasa);
        planilla.setPago_por_grasa(pagoGrasa);
        planilla.setPct_solidos_totales(pctSolidosTotales);
        planilla.setPago_por_solidos_totales(pagoSolidosTotales);
        planilla.setNro_dias_envio_leche(nroDiasEnvioLeche);
        planilla.setPromedio_diario_kls_leche(promDiarioKlsLeche);
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


