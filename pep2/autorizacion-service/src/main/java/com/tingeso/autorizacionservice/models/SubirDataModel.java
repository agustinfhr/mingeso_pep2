package com.tingeso.autorizacionservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubirDataModel {
    private String fecha;
    private String turno;
    private String proveedor;
    private String kls_leche;

}