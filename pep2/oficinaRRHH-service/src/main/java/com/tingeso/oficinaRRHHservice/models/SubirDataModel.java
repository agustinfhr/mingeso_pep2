package com.tingeso.oficinaRRHHservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubirDataModel {
    private Integer ID;
    private String fecha;
    private String turno;
    private String proveedor;
    private String kls_leche;

}