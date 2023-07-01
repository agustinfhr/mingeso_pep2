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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    /*
    public String calculoFechaQuincena(String codigo) {
        List<String> proveedorData = restTemplate.getForObject("http://subir-data-service/subir-data/fecha/" + codigo, List.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(proveedorData.get(0), formatter);
        int dd = localDate.getDayOfMonth();
        //int mm = localDate.getMonthValue();
        //int yyyy = localDate.getYear();

        if (dd <= 15) {
            return String.valueOf(15);

        } else {
            return String.valueOf(31);
        }
    }

    */
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

    public int variacionLeche(String codigo, Integer totalKlsLeche){

        Integer variacionLecheAnterior = 0;

        if (planillaRepository.existsAny(codigo)) {
            ArrayList<PlanillaEntity> planillas = planillaRepository.findByCodigo_proveedorOrderByQuincenaDesc(codigo);
            PlanillaEntity firstPlanilla = planillas.get(0);
            variacionLecheAnterior = firstPlanilla.getTotal_kls_leche();
        }

        if (variacionLecheAnterior == 0) {
            return 0;
        } else {
            int pctVariacionLeche = ((totalKlsLeche * 100) / variacionLecheAnterior) - 100;

            return pctVariacionLeche;
        }


    }

    public int variacionGrasa(String codigo, Integer pctGrasa){

        Integer variacionGrasaAnterior = 0;

        if (planillaRepository.existsAny(codigo)) {
            ArrayList<PlanillaEntity> planillas = planillaRepository.findByCodigo_proveedorOrderByQuincenaDesc(codigo);
            PlanillaEntity firstPlanilla = planillas.get(0);
            variacionGrasaAnterior = firstPlanilla.getPct_grasa();
        }

        if (variacionGrasaAnterior == 0) {
            return 0;
        } else {
            int pctVariacionGrasa = ((pctGrasa * 100) / variacionGrasaAnterior) - 100;

            return pctVariacionGrasa;
        }

    }

    public int variacionST(String codigo, Integer pctSolidosTotales){

        Integer variacionSolidosTotalesAnterior = 0;

        if (planillaRepository.existsAny(codigo)) {
            ArrayList<PlanillaEntity> planillas = planillaRepository.findByCodigo_proveedorOrderByQuincenaDesc(codigo);
            PlanillaEntity firstPlanilla = planillas.get(0);
            variacionSolidosTotalesAnterior = firstPlanilla.getPct_solidos_totales();
        }

        if (variacionSolidosTotalesAnterior == 0) {
            return 0;
        } else {
            int pctVariacionST = ((pctSolidosTotales * 100) / variacionSolidosTotalesAnterior) - 100;

            return pctVariacionST;
        }


    }

    public int mananaTarde(String codigo){

        Integer turno = 0;
        List<String> proveedorDatas = restTemplate.getForObject("http://subir-data-service/subir-data/turno/" + codigo, List.class);
        for (String proveedorData : proveedorDatas) {
            if (proveedorData.equals("M")) {
                if (turno == 2) {
                    return 3;
                }
                turno = 1; //manana M
            } else {
                if (turno == 1) {
                    return 3;
                }
                turno = 2; //tarde T
            }
        }
        return turno;
    }

    public int bonificacionFrec(Integer turno, Integer pagoLeche, Integer nroDiasEnvioLeche) {

        if (nroDiasEnvioLeche >= 10) {
            if (turno == 3) {
                int bonificacionFrecuencia = (pagoLeche * 20) / 100;
                return bonificacionFrecuencia;
            } else if (turno == 1) {
                int bonificacionFrecuencia = (pagoLeche * 12) / 100;
                return bonificacionFrecuencia;
            } else if (turno == 2) {
                int bonificacionFrecuencia = (pagoLeche * 8) / 100;
                return bonificacionFrecuencia;
            } else {
                return 0;
            }
        } else {
            return 0;
        }

    }

    public int variacionNegativaLeche(Integer pctVariacionLeche, Integer pagoAcopioLeche){

        if (pctVariacionLeche >= -8) {
            int dctoVariacionLeche = 0;
            return dctoVariacionLeche;
        } else if (pctVariacionLeche >= -25 && pctVariacionLeche <= -9) {
            int dctoVariacionLeche = (pagoAcopioLeche * 7) / 100;
            return dctoVariacionLeche;
        } else if (pctVariacionLeche >= -45 && pctVariacionLeche <= -26) {
            int dctoVariacionLeche = (pagoAcopioLeche * 15) / 100;
            return dctoVariacionLeche;
        } else if (pctVariacionLeche <= -46) {
            int dctoVariacionLeche = (pagoAcopioLeche * 30) / 100;
            return dctoVariacionLeche;
        } else {
            return 0;
        }

    }

    public int variacionNegativaGrasa(Integer pctVariacionGrasa, Integer pagoAcopioLeche){

        if (pctVariacionGrasa >= -15) {
            int dctoVariacionGrasa = 0;
            return dctoVariacionGrasa;
        } else if (pctVariacionGrasa >= -25 && pctVariacionGrasa <= -16) {
            int dctoVariacionGrasa = (pagoAcopioLeche * 7) / 100;
            return dctoVariacionGrasa;
        } else if (pctVariacionGrasa >= -40 && pctVariacionGrasa <= -26) {
            int dctoVariacionGrasa = (pagoAcopioLeche * 15) / 100;
            return dctoVariacionGrasa;
        } else if (pctVariacionGrasa <= -41) {
            int dctoVariacionGrasa = (pagoAcopioLeche * 30) / 100;
            return dctoVariacionGrasa;
        } else {
            return 0;
        }
    }


    public int variacionNegativaST(Integer pctVariacionST, Integer pagoAcopioLeche){

        if (pctVariacionST >= -6) {

            return 0;
        } else if (pctVariacionST >= -12 && pctVariacionST <= -7) {

            return ((pagoAcopioLeche * 7) / 100);
        } else if (pctVariacionST >= -35 && pctVariacionST <= -13) {

            return ((pagoAcopioLeche * 15) / 100);
        } else if (pctVariacionST <= -36) {

            return ((pagoAcopioLeche * 30) / 100);
        } else {

            return 0;
        }

    }


    public int impuestoRetencion(String retencion, Integer pagoTotal){

        if (retencion.equals("Si")) {

            if (pagoTotal >= 950000) {

                return ((pagoTotal * 13) / 100);
            } else {

                return 0;
            }
        } else {

            return 0;
        }

    }


    public void calculoPlanilla(String codigo) throws ParseException {

        ProveedorModel proveedorActual = obtenerProveedorPorCodigo(codigo);

        //String quincena = "15";
        int totalKlsLeche = klsLeche(codigo);
        int pagoLeche = pagoPorLeche(proveedorActual.getCategoria(), totalKlsLeche);
        int pctGrasa = pagoPorGrasa(totalKlsLeche,codigo,1);
        int pagoGrasa = pagoPorGrasa(totalKlsLeche,codigo,0);
        int pctSolidosTotales = pagoPorST(totalKlsLeche,codigo,1);
        int pagoSolidosTotales = pagoPorST(totalKlsLeche,codigo,0);
        int nroDiasEnvioLeche = totalNroDiasLeche(codigo);
        int promDiarioKlsLeche = promklsLeche(totalKlsLeche,nroDiasEnvioLeche);
        int pctVariacionLeche = variacionLeche(codigo, totalKlsLeche);
        int pctVariacionGrasa = variacionGrasa(codigo, pctGrasa);
        int pctVariacionST = variacionST(codigo, pctSolidosTotales);
        int turno = mananaTarde(codigo);
        int bonificacionFrecuencia = bonificacionFrec(turno, pagoLeche, nroDiasEnvioLeche);
        int pagoAcopioLeche = pagoLeche + pagoGrasa + pagoSolidosTotales + bonificacionFrecuencia;
        int dctoVariacionLeche = variacionNegativaLeche(pctVariacionLeche, pagoAcopioLeche);
        int dctoVariacionGrasa = variacionNegativaGrasa(pctVariacionGrasa, pagoAcopioLeche);
        int dctoVariacionST = variacionNegativaST(pctVariacionST, pagoAcopioLeche);
        int descuentos = dctoVariacionLeche + dctoVariacionGrasa + dctoVariacionST;
        int pagoTotal = pagoAcopioLeche - descuentos;
        int montoRetencion = impuestoRetencion(proveedorActual.getRetencion(), pagoTotal);
        int montoFinal = pagoTotal - montoRetencion;

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
        planilla.setPct_variacion_leche(pctVariacionLeche);
        planilla.setPct_variacion_grasa(pctVariacionGrasa);
        planilla.setPct_variacion_st(pctVariacionST);
        planilla.setBonificacion_frecuencia(bonificacionFrecuencia);
        planilla.setDcto_variacion_leche(dctoVariacionLeche);
        planilla.setDcto_variacion_grasa(dctoVariacionGrasa);
        planilla.setDcto_variacion_st(dctoVariacionST);
        planilla.setPago_total(pagoTotal);
        planilla.setMonto_retencion(montoRetencion);
        planilla.setMonto_final(montoFinal);

        planillaRepository.save(planilla);



    }



}


