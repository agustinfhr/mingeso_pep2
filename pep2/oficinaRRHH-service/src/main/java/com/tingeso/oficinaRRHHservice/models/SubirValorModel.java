package com.tingeso.oficinaRRHHservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubirValorModel {
    private Integer ID;
    private String proveedor;
    private String pct_grasa;
    private String pct_solido_total;
}
